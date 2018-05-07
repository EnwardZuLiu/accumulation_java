package xyz.liuzm.accumulation.kubernetes.fabric8io;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * 使用 fabric8io 来访问 kubernetes 集群
 */
public class KubernetesFabric {

    /**
     * 用来测试 kebernetes
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        KubernetesClient client = new DefaultKubernetesClient();
//        NamespaceList namespaceList = client.namespaces().list();
        PodList list = client.pods().list();
        System.out.println(list);
    }

}
