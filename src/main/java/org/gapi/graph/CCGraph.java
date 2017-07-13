package org.gapi.graph;

import org.gapi.common.ConnectedComp;
import org.gapi.common.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Vertices of undirected graph could be partitioned into separated connected components.
 * <p/>
 * <p/>
 * The class Graph visualizes a graph as a map between vertex and associated connected component
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 7/8/17
 */
public class CCGraph<T> {

    private final Map<String, ConnectedComp<T>> vtxToConComMap;

    private final AtomicInteger compIdGen = new AtomicInteger(0);

    public CCGraph() {
        vtxToConComMap = new HashMap<String, ConnectedComp<T>>();
    }

    public void addEdge(Vertex<T> src, Vertex<T> dst) {
        String srcId = src.getId();
        String dstId = dst.getId();
        ConnectedComp<T> srcComp = vtxToConComMap.get(srcId);
        ConnectedComp<T> dstComp = vtxToConComMap.get(dstId);

        if (srcComp == null && dstComp == null) {

            //Two standalone nodes are connected, they found a new connected component
            String id = "Comp:" + compIdGen.addAndGet(1);

            ConnectedComp<T> twoNodesComp = new ConnectedComp<T>(id);
            twoNodesComp.addNode(src);
            twoNodesComp.addNode(dst);

            vtxToConComMap.put(srcId, twoNodesComp);
            vtxToConComMap.put(dstId, twoNodesComp);

        } else if (srcComp == null) {
            dstComp.addNode(src);
            vtxToConComMap.put(srcId, dstComp);

        } else if (dstComp == null) {
            srcComp.addNode(dst);
            vtxToConComMap.put(dstId, srcComp);

        } else {
            //Two separated connected components are connected
            if (!srcComp.getId().equals(dstComp.getId())) {
                mergeConComps(srcComp, dstComp);
            }
        }
    }

    /**
     * Checking connectivity between two vertices
     *
     * @param src
     * @param dst
     * @return
     */
    public boolean query(Vertex<T> src, Vertex<T> dst) {
        ConnectedComp<T> srcComp = vtxToConComMap.get(src.getId());
        ConnectedComp<T> dstComp = vtxToConComMap.get(dst.getId());

        return srcComp != null && dstComp != null && srcComp.getId().equals(dstComp.getId());
    }

    public List<ConnectedComp<T>> getComponents() {
        final Map<String, Boolean> addedComps = new HashMap<String, Boolean>();
        final List<ConnectedComp<T>> comps = new LinkedList<ConnectedComp<T>>();
        for (Map.Entry<String, ConnectedComp<T>> e : vtxToConComMap.entrySet()) {
            ConnectedComp<T> comp = e.getValue();

            if (addedComps.get(comp.getId()) == null) {
                comps.add(comp);
                addedComps.put(comp.getId(), true);
            }
        }

        return comps;
    }

    private void mergeConComps(ConnectedComp<T> firstComp, ConnectedComp<T> secondComp) {
        ConnectedComp<T> newComp = firstComp.mergeWith(secondComp);

        for (Vertex<T> node : newComp.getNodes()) {
            vtxToConComMap.put(node.getId(), newComp);
        }
    }

}
