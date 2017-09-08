package com.yn.utils;

import com.yn.component.AndroidManifest;
import com.yn.component.bean.Attribute;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

/**
 * Created by Whyn on 2017/8/30.
 */

public final class Utils {
    private Utils() {
    }

    private static Messager sMessager;

    public static void init(Messager messager) {
        sMessager = messager;
    }

    public static void note(String msg) {
        checkMessager();
        sMessager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

    public static void note(String format, Object... args) {
        note(String.format(format, args));
    }

    public static void warn(String msg) {
        checkMessager();
        sMessager.printMessage(Diagnostic.Kind.WARNING, msg);
    }

    public static void warn(String format, Object... args) {
        warn(String.format(format, args));
    }

    public static void error(String msg) {
        checkMessager();
        sMessager.printMessage(Diagnostic.Kind.ERROR, msg);
    }

    public static void error(String format, Object... args) {
        error(String.format(format, args));
    }

    public static void error(Element element, String msg) {
        checkMessager();
        sMessager.printMessage(Diagnostic.Kind.ERROR, msg, element);
    }

    public static void error(Element element, String format, Object... args) {
        error(element, String.format(format, args));
    }

    private static void checkMessager() {
        checkNotNull(sMessager, "Messager is null,did you forget to call Utils.init()");
    }

    public static  void checkNotNull(Object target, String errorMsg) {
        if (target != null)
            return;
        throw new IllegalArgumentException(errorMsg);
    }

    public static void checkNotNull(Object target, String format, Object... args) {
        if (target != null)
            return;
        throw new IllegalArgumentException(String.format(format, args));
    }


    public static void close(Writer writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T, V> boolean isSameType(T source, V other) {
        if (source == null && other == null)
            return true;
        if (source == null || other == null)
            return false;
//        return source.getClass().isAssignableFrom(other.getClass());
        //must be the exact same type
        return source.getClass().getCanonicalName().equals(other.getClass().getCanonicalName());
    }

    //from butterknife
    public static boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (isTypeEqual(typeMirror, otherType)) {
            return true;
        }
        /**
         * DECLARED
         * A class or interface type.
         */
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() > 0) {
            StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
            typeString.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                if (i > 0) {
                    typeString.append(',');
                }
                typeString.append('?');
            }
            typeString.append('>');
            if (typeString.toString().equals(otherType)) {
                return true;
            }
        }
        Element element = declaredType.asElement();
        if (!(element instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = (TypeElement) element;
        TypeMirror superType = typeElement.getSuperclass();
        if (isSubtypeOfType(superType, otherType)) {
            return true;
        }
        for (TypeMirror interfaceType : typeElement.getInterfaces()) {
            if (isSubtypeOfType(interfaceType, otherType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTypeEqual(TypeMirror typeMirror, String otherType) {
        return otherType.equals(typeMirror.toString());
    }

    public static String getProperName(final String pkName, final String annotatedName) {
        if (pkName == null || annotatedName == null)
            return annotatedName;
        return !annotatedName.startsWith(AndroidManifest.DOT) ? annotatedName : pkName + annotatedName;
    }

    public static <V extends Attribute> String getValueFromCollection(final String key, Collection<? extends V> collection) {
        for (V item : collection) {
            if (item.key.equals(key))
                return item.value;
        }
        return null;
    }

    public static <T> T getSameItemFromCollection(Collection<? extends T> collection, T newItem) {
        for (T item : collection) {
            if (item.equals(newItem))
                return item;
        }
        return null;
    }

    public static boolean isEmpty(String newData) {
        return newData == null || newData.isEmpty();
    }
}
