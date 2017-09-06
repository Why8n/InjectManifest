package com.yn.compiler;

import com.google.auto.service.AutoService;
import com.yn.annotations.InjectActivity;
import com.yn.annotations.InjectApp;
import com.yn.annotations.InjectIntentFilter;
import com.yn.annotations.InjectPermissions;
import com.yn.annotations.InjectReceiver;
import com.yn.annotations.InjectService;
import com.yn.component.AndroidManifest;
import com.yn.component.Collections;
import com.yn.component.NodeActivity;
import com.yn.component.NodeApp;
import com.yn.component.NodeReceiver;
import com.yn.component.NodeService;
import com.yn.define.ConstValue;
import com.yn.utils.Utils;
import com.yn.xmls.factory.XmlFactory;
import com.yn.xmls.interfaces.IXml;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.StandardLocation;

import static com.yn.define.ConstValue.TAG_ACTIVITY;
import static com.yn.define.ConstValue.TAG_APPLICATION;
import static com.yn.define.ConstValue.TAG_MANIFEST;
import static com.yn.define.ConstValue.TAG_PERMISSION;
import static com.yn.define.ConstValue.TAG_RECEIVER;
import static com.yn.define.ConstValue.TAG_SERVICE;

@AutoService(Processor.class)
public class ManifestCollectionProcessor extends AbstractProcessor {
    private static final String ANDROID_MANIFEST_XML = "AndroidManifest.xml";
    private static final String OPTIONS_ANDROID_MANIFEST_PATH = "AndroidManifestPath";

    private static final String TYPE_APP = "android.app.Application";
    private static final String TYPE_ACTIVITY = "android.app.Activity";
    private static final String TYPE_SERVICE = "android.app.Service";
    private static final String TYPE_RECEIVER = "android.content.BroadcastReceiver";

    private Elements mElementUtils;
    private boolean isNeedGenerateXml;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        Utils.init(processingEnvironment.getMessager());
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(InjectPermissions.class.getCanonicalName());
        annotations.add(InjectActivity.class.getCanonicalName());
        annotations.add(InjectService.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedOptions() {
        Set<String> options = new HashSet<>();
        options.add(OPTIONS_ANDROID_MANIFEST_PATH);
        return options;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        isNeedGenerateXml = false;
        final Collections androidManifest = preparedAndroidManifest();
        IXml xmlDecoder = null;

        String androidManifestPath = processingEnv.getOptions().get(OPTIONS_ANDROID_MANIFEST_PATH);
        Utils.note("options:AndroidManifestPath: %s", androidManifestPath);
        //make it first,so that we can override fields via later annotation decode process.
        if (androidManifestPath != null) {
            xmlDecoder = parseXml(androidManifest, androidManifestPath);
        }

        parseApp(roundEnvironment, androidManifest);
        parsePermission(roundEnvironment, androidManifest);
        parseActivity(roundEnvironment, androidManifest);
        parseService(roundEnvironment, androidManifest);
        parseReceiver(roundEnvironment, androidManifest);

        return isNeedGenerateXml ? generatedXml(xmlDecoder) : false;

    }

    private Collections preparedAndroidManifest() {
        Collections manifest = new Collections();
        manifest.plantComponent(new AndroidManifest.ManifestCollection(), TAG_MANIFEST);
        manifest.plantComponent(new AndroidManifest.PermissionCollection(), TAG_PERMISSION);
        manifest.plantComponent(new AndroidManifest.ApplicationCollection(), TAG_APPLICATION);
        manifest.plantComponent(new AndroidManifest.ActivityCollection(), TAG_ACTIVITY);
        manifest.plantComponent(new AndroidManifest.ServiceCollection(), TAG_SERVICE);
        manifest.plantComponent(new AndroidManifest.ReceiverCollection().isLastElement(true), TAG_RECEIVER);
        return manifest;
    }

    private IXml parseXml(Collections androidManifest, String androidManifestPath) {
        IXml xmlDecoder = XmlFactory.createXmlDecoder(androidManifest);
        xmlDecoder.parseXml(androidManifestPath);
        return xmlDecoder;
    }


    private boolean generatedXml(IXml xmlDecoder) {
        boolean bRet = true;
        Writer writer = null;
        try {
            writer = processingEnv.getFiler().createResource(
                    StandardLocation.SOURCE_OUTPUT, "", ANDROID_MANIFEST_XML).openWriter();
            xmlDecoder.createXml(writer);
        } catch (IOException e) {
            e.printStackTrace();
            bRet = false;
        } finally {
            if (writer != null) {
                Utils.close(writer);
            }
        }
        Utils.note("generate AndroidManifest.xml %s", bRet ? "successfully" : "failed");
        return bRet;
    }

    private void parseReceiver(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ReceiverCollection receiverCollection =
                (AndroidManifest.ReceiverCollection) manifest.getTag(TAG_RECEIVER);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectReceiver.class)) {
            if (!isSubtypeOfType(element, TYPE_RECEIVER))
                continue;
            isNeedGenerateXml = true;
            InjectReceiver receiver = element.getAnnotation(InjectReceiver.class);
            parseReceiver(receiverCollection, receiver, element);

        }
    }

    private void parseReceiver(AndroidManifest.ReceiverCollection receiverCollection, InjectReceiver receiver, Element element) {
        String receiverName = Utils.getProperName(mElementUtils.getPackageOf(element).getQualifiedName().toString(),
                receiver.name());
        receiverCollection.collect((NodeReceiver) new NodeReceiver(receiverName)
                .addAttr(ConstValue.KEY_ATTR_NAME, receiverName)
                .addAttr(ConstValue.KEY_ATTR_LABEL, receiver.label())
        );
    }

    private void parseService(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ServiceCollection serviceCollection =
                (AndroidManifest.ServiceCollection) manifest.getTag(TAG_SERVICE);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectService.class)) {
            if (!isSubtypeOfType(element, TYPE_SERVICE))
                continue;
            isNeedGenerateXml = true;
            InjectService service = element.getAnnotation(InjectService.class);
            parseService(serviceCollection, service, element);
        }
    }

    private void parseService(AndroidManifest.ServiceCollection serviceCollection, InjectService service, Element element) {
        String serviceName = Utils.getProperName(mElementUtils.getPackageOf(element).toString(), service.name());
        NodeService nodeService = (NodeService) new NodeService(serviceName)
                .addAttr(ConstValue.KEY_ATTR_NAME, serviceName)
                .addAttr(ConstValue.KEY_ATTR_LABEL, service.label());
        serviceCollection.collect(nodeService);
    }

    private void parseActivity(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ActivityCollection activityCollection =
                (AndroidManifest.ActivityCollection) manifest.getTag(TAG_ACTIVITY);
        checkCollection(activityCollection, AndroidManifest.ActivityCollection.class, TAG_ACTIVITY);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectActivity.class)) {
            if (!isSubtypeOfType(element, TYPE_ACTIVITY))
                continue;
            InjectActivity activity = element.getAnnotation(InjectActivity.class);
            parseActivity(activityCollection, activity, element);
            isNeedGenerateXml = true;
        }
    }


    private void parseActivity(AndroidManifest.ActivityCollection collections,
                               InjectActivity activity,
                               Element element) {
        String activityName = Utils.getProperName(
                mElementUtils.getPackageOf(element).getQualifiedName().toString(), activity.name());
        NodeActivity nodeActivity = (NodeActivity) (new NodeActivity(activityName)
                .addAttr(ConstValue.KEY_ATTR_NAME, activityName))
                .addAttr(ConstValue.KEY_ATTR_LABEL, activity.label());
        InjectIntentFilter intentFilter = activity.getClass().getAnnotation(InjectIntentFilter.class);
        if (intentFilter != null) {
            String[] actions = intentFilter.action();
            nodeActivity.addCategory(ConstValue.KEY_ATTR_NAME, intentFilter.category());
            for (int i = 0, length = actions.length; i < length; ++i) {
                nodeActivity.addAction(ConstValue.KEY_ATTR_NAME, actions[i]);
            }
        }
        collections.collect(nodeActivity);
    }

    private void parseApp(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ApplicationCollection appCollection =
                (AndroidManifest.ApplicationCollection) manifest.getTag(TAG_APPLICATION);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectApp.class)) {
            if (!isSubtypeOfType(element, TYPE_APP))
                continue;
            InjectApp app = element.getAnnotation(InjectApp.class);
            parseApp(appCollection, app, element);
        }
    }

    private void parseApp(AndroidManifest.ApplicationCollection appCollection, InjectApp app, Element element) {

        String appName = Utils.getProperName(mElementUtils.getPackageOf(element).toString(), app.name());
        appCollection.collect(
                new NodeApp()
                        .setName(appName)
                        .setLabel(app.label())
        );
    }

    private void parsePermission(RoundEnvironment roundEnvironment, final Collections manifest) {
        AndroidManifest.PermissionCollection permissionCollection =
                (AndroidManifest.PermissionCollection) manifest.getTag(TAG_PERMISSION);
        checkCollection(permissionCollection, AndroidManifest.PermissionCollection.class, TAG_PERMISSION);

        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectPermissions.class)) {
            InjectPermissions permissions = element.getAnnotation(InjectPermissions.class);
            parsePermission(permissionCollection, permissions);
            isNeedGenerateXml = true;
        }
    }

    private void parsePermission(AndroidManifest.PermissionCollection collections, InjectPermissions permissions) {
        for (String permission : permissions.value()) {
            collections.collect(permission);
        }
    }

    private void checkCollection(AndroidManifest<?> collection,
                                 Class<? extends AndroidManifest> collectionCls,
                                 String tag) {
        Utils.checkNotNull(collection, "can't find %s via tag:%s,please using %s.plantComponent" +
                        "(AndroidManifest,Object) to plant a component with tag",
                collectionCls.getSimpleName(),
                tag, Collections.class.getSimpleName());
    }

    private boolean isSubtypeOfType(final Element element, final String desireType) {
        boolean isSubType;
        if (!(isSubType = Utils.isSubtypeOfType(element.asType(), desireType))) {
            Utils.warn("%s must extend from $s", element.getSimpleName(), desireType);
        }
        return isSubType;
    }
}
