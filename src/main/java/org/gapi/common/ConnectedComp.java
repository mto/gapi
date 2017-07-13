package org.gapi.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a connected component in an undirected graph
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 7/7/17
 */
public class ConnectedComp<T> {

    private Set<Vertex<T>> nodes;

    private final String id;

    public ConnectedComp(String _id) {
        id = _id;
        nodes = new HashSet<Vertex<T>>();
    }

    public void addNode(Vertex<T> node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public String getId() {
        return id;
    }

    public Set<Vertex<T>> getNodes(){
        return nodes;
    }

    /**
     * Merge one connected component with another connected component
     *
     * @param conComp
     * @return
     */
    public ConnectedComp<T> mergeWith(ConnectedComp<T> conComp) {
        ConnectedComp<T> newComp = new ConnectedComp<T>(this.id + "$" + conComp.getId());
        newComp.nodes.addAll(this.nodes);
        newComp.nodes.addAll(conComp.nodes);

        return newComp;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ConnectedComp) && this.id.equals(((ConnectedComp) obj).id);
    }
}
