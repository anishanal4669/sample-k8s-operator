package com.anish.poc.operator;

import com.anish.poc.operator.resource.SampleDomainResource;
import com.anish.poc.operator.resource.SampleDomainResourceList;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.*;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class SampleOperator {

//    private final Map<String, SampleDomainResource> cache = new ConcurrentHashMap<>();
//
//    private NonNamespaceOperation<SampleDomainResource, SampleDomainResourceList, Resource<SampleDomainResource>> customResourceClient;
//
//    private final BlockingDeque<EventItem> transferQueue = new LinkedBlockingDeque<>();
//
//    public SampleOperator(String namespace, KubernetesClient client){
//        this.customResourceClient = client.customResources(SampleDomainResource.class, SampleDomainResourceList.class)
//                .inNamespace(namespace);
//        new Thread(new ResourceEventProcessor(client, namespace, transferQueue)).start();
//    }

    public static void main(String[] args) {
        Config config = new ConfigBuilder().build();
        KubernetesClient client = new DefaultKubernetesClient(config);

        String namespace = client.getNamespace();
        System.out.println("Running in Namespace " +namespace);
        KubernetesDeserializer.registerCustomKind(HasMetadata.getApiVersion(SampleDomainResource.class), "Domain", SampleDomainResource.class);

        client.apiextensions().v1().customResourceDefinitions().list().getItems().stream().filter(
                d -> "domains.poc.anish.com".equals(d.getMetadata().getName())).findAny()
                .orElseThrow(() -> new RuntimeException(" Deployment error: custom resource definition " +
                        "domain for anish.com not found")
        );
        //SampleOperator operator = new SampleOperator(namespace, client);
        client.customResources(SampleDomainResource.class, SampleDomainResourceList.class)
                .inNamespace(namespace).watch(new Watcher<SampleDomainResource>() {
            @Override
            public void eventReceived(Action action, SampleDomainResource resource) {
                System.out.println("Received " + action + " event for resource " + resource);
            }

            @Override
            public void onClose(WatcherException e) {
                if (e != null) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        });
    }
}
