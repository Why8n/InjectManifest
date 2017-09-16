package com.yn.compiler;

import com.google.auto.service.AutoService;
import com.yn.annotations.InjectActivity;
import com.yn.annotations.InjectApp;
import com.yn.annotations.InjectData;
import com.yn.annotations.InjectGrantUriPermission;
import com.yn.annotations.InjectIntentFilter;
import com.yn.annotations.InjectManifest;
import com.yn.annotations.InjectMetaData;
import com.yn.annotations.InjectPathPermission;
import com.yn.annotations.InjectProvider;
import com.yn.annotations.InjectReceiver;
import com.yn.annotations.InjectService;
import com.yn.annotations.InjectUsesPermission;
import com.yn.annotations.InjectUsesPermissionDetail;
import com.yn.component.AndroidManifest;
import com.yn.component.Collections;
import com.yn.component.ComponentBasic;
import com.yn.component.NodeActivity;
import com.yn.component.NodeApp;
import com.yn.component.NodeGrantUriPermission;
import com.yn.component.NodeManifest;
import com.yn.component.NodePathPermission;
import com.yn.component.NodeProvider;
import com.yn.component.NodeReceiver;
import com.yn.component.NodeService;
import com.yn.component.NodeUsesPermission;
import com.yn.component.bean.DataAttribute;
import com.yn.component.bean.MetaData;
import com.yn.utils.Utils;
import com.yn.xmls.factory.XmlFactory;
import com.yn.xmls.interfaces.IXml;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.StandardLocation;

@AutoService(Processor.class)
public class ManifestCollectionProcessor extends AbstractProcessor {
    private static final String ANDROID_MANIFEST_XML = "AndroidManifest.xml";
    private static final String OPTIONS_ANDROID_MANIFEST_PATH = "AndroidManifestPath";

    private static final String TYPE_APP = "android.app.Application";
    private static final String TYPE_ACTIVITY = "android.app.Activity";
    private static final String TYPE_SERVICE = "android.app.Service";
    private static final String TYPE_RECEIVER = "android.content.BroadcastReceiver";
    private static final String TYPE_PROVIDER = "android.content.ContentProvider";

    private static final String TAG_MANIFEST = "tag_manifest";
    private static final String TAG_APPLICATION = "tag_application";
    private static final String TAG_PERMISSION = "tag_permission";
    private static final String TAG_ACTIVITY = "tag_activity";
    private static final String TAG_SERVICE = "tag_service";
    private static final String TAG_RECEIVER = "tag_receiver";
    private static final String TAG_PROVIDER = "tag_provider";

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
        annotations.add(InjectManifest.class.getCanonicalName());
        annotations.add(InjectApp.class.getCanonicalName());
        annotations.add(InjectActivity.class.getCanonicalName());
        annotations.add(InjectService.class.getCanonicalName());
        annotations.add(InjectReceiver.class.getCanonicalName());
        annotations.add(InjectProvider.class.getCanonicalName());
        annotations.add(InjectUsesPermission.class.getCanonicalName());
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

        if (roundEnvironment.processingOver())
            return false;
        isNeedGenerateXml = false;
        final Collections androidManifest = preparedAndroidManifest();
        IXml xmlDecoder = null;

        String androidManifestPath = processingEnv.getOptions().get(OPTIONS_ANDROID_MANIFEST_PATH);
        Utils.note("options:AndroidManifestPath: %s", androidManifestPath);
        //make it first,so that we can override fields via later annotation decode process.
        if (androidManifestPath != null) {
            xmlDecoder = parseXml(androidManifest, androidManifestPath);
        }

        if (!parseManifest(roundEnvironment, androidManifest))
            return false;
        if (!parseApp(roundEnvironment, androidManifest))
            return false;
        if (!parseActivity(roundEnvironment, androidManifest))
            return false;
        if (!parseService(roundEnvironment, androidManifest))
            return false;
        if (!parseReceiver(roundEnvironment, androidManifest))
            return false;
        if (!parseProvider(roundEnvironment, androidManifest))
            return false;
        parsePermission(roundEnvironment, androidManifest);
        if (xmlDecoder == null)
            xmlDecoder = XmlFactory.createXmlDecoder(androidManifest);

        return isNeedGenerateXml && generatedXml(xmlDecoder);

    }


    private Collections preparedAndroidManifest() {
        Collections manifest = new Collections();
        manifest.plantComponent(new AndroidManifest.ManifestCollection(), TAG_MANIFEST);
        manifest.plantComponent(new AndroidManifest.UsesPermissionCollection(), TAG_PERMISSION);
        manifest.plantComponent(new AndroidManifest.ApplicationCollection(), TAG_APPLICATION);
        manifest.plantComponent(new AndroidManifest.ActivityCollection(), TAG_ACTIVITY);
        manifest.plantComponent(new AndroidManifest.ServiceCollection(), TAG_SERVICE);
        manifest.plantComponent(new AndroidManifest.ProviderCollection(), TAG_PROVIDER);
        manifest.plantComponent(new AndroidManifest.ReceiverCollection().isLastElement(true), TAG_RECEIVER);
        return manifest;
    }

    private boolean parseManifest(RoundEnvironment roundEnvironment, Collections manifest) {
        Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(InjectManifest.class);
        if (!checkIsSingle(annotatedElements, InjectManifest.class))
            return false;
        AndroidManifest.ManifestCollection manifestCollection = (AndroidManifest.ManifestCollection) manifest.getTag(TAG_MANIFEST);
        checkCollection(manifestCollection, AndroidManifest.ManifestCollection.class, TAG_MANIFEST);
        for (Element element : annotatedElements) {
            if (!isClass(element, InjectManifest.class))
                return false;
            isNeedGenerateXml = true;
            InjectManifest injectManifest = element.getAnnotation(InjectManifest.class);
            parseManifest(manifestCollection, injectManifest);
        }
        return true;
    }

    private void parseManifest(AndroidManifest.ManifestCollection manifestCollection, InjectManifest manifest) {
        manifestCollection.collect(
                new NodeManifest()
                        .xmlns(manifest.xmlns())
                        .setPackage(manifest.pkName())
                        .sharedUserId(manifest.sharedUserId())
                        .sharedUserLabel(manifest.sharedUserLabel())
                        .versionCode(manifest.versionCode())
                        .versionName(manifest.versionName())
                        .installLocation(manifest.installLocation().getName())
        );
    }


    private boolean parseReceiver(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ReceiverCollection receiverCollection =
                (AndroidManifest.ReceiverCollection) manifest.getTag(TAG_RECEIVER);
        checkCollection(receiverCollection, AndroidManifest.ReceiverCollection.class, TAG_RECEIVER);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectReceiver.class)) {
            if (!isClass(element, InjectReceiver.class) || !isSubtypeOfType(element, TYPE_RECEIVER))
                return false;
            isNeedGenerateXml = true;
            InjectReceiver receiver = element.getAnnotation(InjectReceiver.class);
            parseReceiver(receiverCollection, receiver, element);
        }
        return true;
    }

    private void parseReceiver(AndroidManifest.ReceiverCollection receiverCollection, InjectReceiver receiver, Element element) {
        String receiverName = Utils.getProperName(mElementUtils.getPackageOf(element).getQualifiedName().toString(),
                receiver.name());
        NodeReceiver nodeReceiver = new NodeReceiver(receiverName)
                .name(receiverName)
                .directBootAware(receiver.directBootAware().getResult())
                .enabled(receiver.enabled().getResult())
                .exported(receiver.exported().getResult())
                .icon(receiver.icon())
                .label(receiver.label())
                .permission(receiver.permission())
                .process(receiver.process());
        parseIntentFilter(nodeReceiver, receiver.intentFilter());
        parseMetaData(nodeReceiver, receiver.metaData());
        receiverCollection.collect(nodeReceiver);
    }

    private boolean parseProvider(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ProviderCollection providerCollection =
                (AndroidManifest.ProviderCollection) manifest.getTag(TAG_PROVIDER);
        checkCollection(providerCollection, AndroidManifest.ProviderCollection.class, TAG_PROVIDER);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectProvider.class)) {
            if (!isClass(element, InjectProvider.class) || !isSubtypeOfType(element, TYPE_PROVIDER))
                return false;
            isNeedGenerateXml = true;
            InjectProvider provider = element.getAnnotation(InjectProvider.class);
            parseProvider(providerCollection, provider, element);
        }
        return true;
    }

    private void parseProvider(AndroidManifest.ProviderCollection collection, InjectProvider provider, Element element) {
        String providerName = Utils.getProperName(mElementUtils.getPackageOf(element).toString(), provider.name());
        NodeProvider nodeProvider = new NodeProvider(providerName)
                .name(providerName)
                .authorities(provider.authorities())
                .directBootAware(provider.directBootAware().getResult())
                .enabled(provider.enabled().getResult())
                .exported(provider.exported().getResult())
                .grantUriPermissions(provider.grantUriPermissions().getResult())
                .icon(provider.icon())
                .initOrder(provider.initOrder())
                .label(provider.label())
                .multiprocess(provider.multiprocess().getResult())
                .permission(provider.permission())
                .process(provider.process())
                .readPermission(provider.readPermission())
                .syncable(provider.syncable().getResult());
        parseMetaData(nodeProvider, provider.metaData());
        parseGrantUriPermission(nodeProvider, provider.grantUriPermission());
        parsePathPermission(nodeProvider, provider.pathPermission());
        collection.collect(nodeProvider);
    }

    private void parsePathPermission(NodeProvider nodeProvider, InjectPathPermission[] injectPathPermissions) {
        for (int i = 0, lenth = injectPathPermissions.length; i < lenth; ++i) {
            nodeProvider.addPathPermissions(
                    new NodePathPermission()
                            .<NodePathPermission>path(injectPathPermissions[i].path())
                            .<NodePathPermission>pathPrefix(injectPathPermissions[i].pathPrefix())
                            .<NodePathPermission>pathPattern(injectPathPermissions[i].pathPattern())
                            .permission(injectPathPermissions[i].permission())
                            .readPermission(injectPathPermissions[i].readPermission())
                            .writePermission(injectPathPermissions[i].writePermission())
            );
        }
    }

    private void parseGrantUriPermission(NodeProvider nodeProvider, InjectGrantUriPermission[] injectGrantUriPermissions) {
        for (int i = 0, lenth = injectGrantUriPermissions.length; i < lenth; ++i) {
            nodeProvider.addGrantUriPermission(
                    new NodeGrantUriPermission()
                            .path(injectGrantUriPermissions[i].path())
                            .pathPattern(injectGrantUriPermissions[i].pathPattern())
                            .pathPrefix(injectGrantUriPermissions[i].pathPrefix())
            );
        }
    }


    private boolean parseService(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ServiceCollection serviceCollection =
                (AndroidManifest.ServiceCollection) manifest.getTag(TAG_SERVICE);
        checkCollection(serviceCollection, AndroidManifest.ServiceCollection.class, TAG_SERVICE);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectService.class)) {
            if (!isClass(element, InjectService.class) || !isSubtypeOfType(element, TYPE_SERVICE))
                return false;
            isNeedGenerateXml = true;
            InjectService service = element.getAnnotation(InjectService.class);
            parseService(serviceCollection, service, element);
        }
        return true;
    }

    private void parseService(AndroidManifest.ServiceCollection serviceCollection, InjectService service, Element element) {
        String serviceName = Utils.getProperName(mElementUtils.getPackageOf(element).toString(), service.name());
        NodeService nodeService = new NodeService(serviceName)
                .name(serviceName)
                .description(service.description())
                .directBootAware(service.directBootAware().getResult())
                .enabled(service.enabled().getResult())
                .exported(service.exported().getResult())
                .icon(service.icon())
                .isolatedProcess(service.isolatedProcess().getResult())
                .label(service.label())
                .permission(service.permission())
                .process(service.process());
        parseIntentFilter(nodeService, service.intentFilter());
        parseMetaData(nodeService, service.metaData());
        serviceCollection.collect(nodeService);
    }

    private boolean parseActivity(RoundEnvironment roundEnvironment, Collections manifest) {
        AndroidManifest.ActivityCollection activityCollection =
                (AndroidManifest.ActivityCollection) manifest.getTag(TAG_ACTIVITY);
        checkCollection(activityCollection, AndroidManifest.ActivityCollection.class, TAG_ACTIVITY);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectActivity.class)) {
            if (!isClass(element, InjectActivity.class) || !isSubtypeOfType(element, TYPE_ACTIVITY))
                return false;
            InjectActivity activity = element.getAnnotation(InjectActivity.class);
            parseActivity(activityCollection, activity, element);
            isNeedGenerateXml = true;
        }
        return true;
    }


    private void parseActivity(AndroidManifest.ActivityCollection collections,
                               InjectActivity activity,
                               Element element) {
        String activityName = Utils.getProperName(
                mElementUtils.getPackageOf(element).getQualifiedName().toString(), activity.name());
        NodeActivity nodeActivity = new NodeActivity(activityName)
                .name(activityName)
                .allowEmbedded(activity.allowEmbedded().getResult())
                .allowTaskReparenting(activity.allowTaskReparenting().getResult())
                .alwaysRetainTaskState(activity.alwaysRetainTaskState().getResult())
                .autoRemoveFromRecents(activity.autoRemoveFromRecents().getResult())
                .banner(activity.banner())
                .clearTaskOnLaunch(activity.clearTaskOnLaunch().getResult())
                .configChanges(activity.configChanges())
                .documentLaunchMode(activity.documentLaunchMode())
                .enabled(activity.enabled().getResult())
                .excludeFromRecents(activity.excludeFromRecents().getResult())
                .exported(activity.exported().getResult())
                .finishOnTaskLaunch(activity.finishOnTaskLaunch().getResult())
                .hardwareAccelerated(activity.hardwareAccelerated().getResult())
                .icon(activity.icon())
                .label(activity.label())
                .launchMode(activity.launchMode())
                .maxRecents(activity.maxRecents())
                .multiprocess(activity.multiprocess().getResult())
                .noHistory(activity.noHistory().getResult())
                .parentActivityName(activity.parentActivityName())
                .permission(activity.permission())
                .process(activity.process())
                .relinquishTaskIdentity(activity.relinquishTaskIdentity().getResult())
                .resizeableActivity(activity.resizeableActivity().getResult())
                .screenOrientation(activity.screenOrientation())
                .stateNotNeeded(activity.stateNotNeeded().getResult())
                .supportsPictureInPicture(activity.supportsPictureInPicture().getResult())
                .taskAffinity(activity.taskAffinity())
                .theme(activity.theme())
                .uiOptions(activity.uiOptions())
                .windowSoftInputMode(activity.windowSoftInputMode());
        parseIntentFilter(nodeActivity, activity.intentFilter());
        parseMetaData(nodeActivity, activity.metaData());
        collections.collect(nodeActivity);
    }

    private <T extends ComponentBasic> void parseMetaData(T nodeComponent, InjectMetaData[] injectMetaDatas) {
        String name = null;
        for (InjectMetaData metaData : injectMetaDatas) {
            name = metaData.name();
            if (Utils.isEmpty(name)) {
                Utils.warn("meta-data without a name will be ignored");
                continue;
            }
            nodeComponent.addMetaData(new MetaData()
                    .name(name)
                    .resource(metaData.resource())
                    .value(metaData.value())
            );
        }
    }

    private <T extends NodeApp> void parseMetaData(T nodeComponent, InjectMetaData[] injectMetaDatas) {
        String name = null;
        for (InjectMetaData metaData : injectMetaDatas) {
            name = metaData.name();
            if (Utils.isEmpty(name)) {
                Utils.warn("meta-data without a name will be ignored");
                continue;
            }
            nodeComponent.addMetaData(new MetaData()
                    .name(name)
                    .resource(metaData.resource())
                    .value(metaData.value())
            );
        }
    }

    private <T extends ComponentBasic> void parseIntentFilter(T nodeComponent, InjectIntentFilter intentFilter) {
        if (intentFilter == null)
            return;
        String[] actions = intentFilter.action();
        for (int i = 0, length = actions.length; i < length; ++i) {
            nodeComponent.addAction(AndroidManifest.ActivityCollection.KEY_ATTR_NAME, actions[i]);
        }
        String[] categories = intentFilter.category();
        for (int i = 0, length = categories.length; i < length; ++i) {
            nodeComponent.addCategory(AndroidManifest.ActivityCollection.KEY_ATTR_NAME, categories[i]);
        }
        InjectData[] datas = intentFilter.data();
        for (int i = 0, length = datas.length; i < length; ++i) {
            nodeComponent.addData(
                    new DataAttribute()
                            .scheme(datas[i].scheme())
                            .host(datas[i].host())
                            .port(datas[i].port())
                            .path(datas[i].path())
                            .pathPattern(datas[i].pathPattern())
                            .pathPrefix(datas[i].pathPrefix())
                            .mimeType(datas[i].mimeType())
            );
        }
    }

    private boolean checkIsSingle(Set<? extends Element> annotationElements, Class<? extends Annotation> annotation) {
        if (annotationElements.size() > 1) {
            Utils.error("@%s must be annotated in just one Application class", annotation.getSimpleName());
            return false;
        }
        return true;
    }

    private boolean isClass(Element element, Class<? extends Annotation> annotationCls) {
        if (element.getKind() != ElementKind.CLASS) {
            Utils.error(element, "@%s must be annotated in class", annotationCls.getSimpleName());
            return false;
        }
        return true;
    }

    private boolean parseApp(RoundEnvironment roundEnvironment, Collections manifest) {
        Set<? extends Element> annotationElements = roundEnvironment.getElementsAnnotatedWith(InjectApp.class);
        if (!checkIsSingle(annotationElements, InjectApp.class))
            return false;
        AndroidManifest.ApplicationCollection appCollection =
                (AndroidManifest.ApplicationCollection) manifest.getTag(TAG_APPLICATION);
        checkCollection(appCollection, AndroidManifest.ApplicationCollection.class, TAG_APPLICATION);
        for (Element element : annotationElements) {
            Utils.note("parseApp ------------------------");
            if (!isClass(element, InjectApp.class) || !isSubtypeOfType(element, TYPE_APP))
                return false;
            InjectApp app = element.getAnnotation(InjectApp.class);
            parseApp(appCollection, app, element);
            isNeedGenerateXml = true;
        }
        return true;
    }

    private void parseApp(AndroidManifest.ApplicationCollection appCollection, InjectApp app, Element element) {
        String appName = Utils.getProperName(mElementUtils.getPackageOf(element).toString(), app.name());
        NodeApp nodeApp = new NodeApp(appName)
                .name(appName)
                .allowTaskReparenting(app.allowTaskReparenting().getResult())
                .allowBackup(app.allowBackup().getResult())
                .allowClearUserData(app.allowClearUserData().getResult())
                .backupAgent(app.backupAgent())
                .backupInForeground(app.backupInForeground().getResult())
                .banner(app.banner())
                .debuggable(app.debuggable().getResult())
                .description(app.description())
                .directBootAware(app.directBootAware().getResult())
                .enabled(app.enabled().getResult())
                .extractNativeLibs(app.extractNativeLibs().getResult())
                .fullBackupContent(app.fullBackupContent())
                .fullBackupOnly(app.fullBackupOnly().getResult())
                .hasCode(app.hasCode().getResult())
                .hardwareAccelerated(app.hardwareAccelerated().getResult())
                .icon(app.icon())
                .isGame(app.isGame().getResult())
                .killAfterRestore(app.killAfterRestore().getResult())
                .largeHeap(app.largeHeap().getResult())
                .label(app.label())
                .logo(app.logo())
                .manageSpaceActivity(app.manageSpaceActivity())
                .networkSecurityConfig(app.networkSecurityConfig())
                .permission(app.permission())
                .persistent(app.persistent().getResult())
                .process(app.process())
                .restoreAnyVersion(app.restoreAnyVersion().getResult())
                .requiredAccountType(app.requiredAccountType())
                .resizeableActivity(app.resizeableActivity().getResult())
                .restrictedAccountType(app.restrictedAccountType())
                .supportsRtl(app.supportsRtl().getResult())
                .taskAffinity(app.taskAffinity())
                .testOnly(app.testOnly().getResult())
                .theme(app.theme())
                .allowTaskReparenting(app.allowTaskReparenting().getResult())
                .usesCleartextTraffic(app.usesCleartextTraffic().getResult())
                .vmSafeMode(app.vmSafeMode().getResult());
        parseMetaData(nodeApp, app.metaData());
        appCollection.collect(nodeApp);
    }

    private void parsePermission(RoundEnvironment roundEnvironment, final Collections manifest) {
        AndroidManifest.UsesPermissionCollection usesPermissionCollection =
                (AndroidManifest.UsesPermissionCollection) manifest.getTag(TAG_PERMISSION);
        checkCollection(usesPermissionCollection, AndroidManifest.UsesPermissionCollection.class, TAG_PERMISSION);
        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectUsesPermission.class)) {
            InjectUsesPermission permissions = element.getAnnotation(InjectUsesPermission.class);
            parsePermission(usesPermissionCollection, permissions);
            isNeedGenerateXml = true;
        }
    }

    private void parsePermission(AndroidManifest.UsesPermissionCollection collections, InjectUsesPermission permissions) {
        for (String permission : permissions.value()) {
            collections.collect(new NodeUsesPermission().permission(permission));
        }
        InjectUsesPermissionDetail[] permissionDetails = permissions.permission();
        for (InjectUsesPermissionDetail permissionDetail : permissionDetails) {
            collections.collect(new NodeUsesPermission()
                    .permission(permissionDetail.permission())
                    .maxSdkVersion(permissionDetail.maxSdkVersion())
            );
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
            Utils.warn("%s must extends from $s", element.getSimpleName(), desireType);
        }
        return isSubType;
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
}
