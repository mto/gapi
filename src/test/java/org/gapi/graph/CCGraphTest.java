package org.gapi.graph;

import junit.framework.TestCase;
import org.gapi.common.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 7/8/17
 */
public class CCGraphTest extends TestCase {

    public void testConnectivity() {
        CCGraph<Integer> graph = new CCGraph<Integer>();
        List<Vertex<Integer>> vertices = new ArrayList<Vertex<Integer>>();
        for (int i = 0; i < 9; i++) {
            vertices.set(i, new Vertex<Integer>(i));
        }

        graph.addEdge(vertices.get(1), vertices.get(6));
        graph.addEdge(vertices.get(1), vertices.get(2));
        graph.addEdge(vertices.get(2), vertices.get(6));
        graph.addEdge(vertices.get(2), vertices.get(4));
        graph.addEdge(vertices.get(5), vertices.get(8));

        assertTrue(graph.query(vertices.get(4), vertices.get(6)));
        assertFalse(graph.query(vertices.get(8), vertices.get(6)));
    }

    public void testMergeComponents(){
        CCGraph<Integer> graph = new CCGraph<Integer>();
        List<Vertex<Integer>> vertices = new ArrayList<Vertex<Integer>>();
        for (int i = 0; i < 9; i++) {
            vertices.set(i, new Vertex<Integer>(i));
        }

        graph.addEdge(vertices.get(1), vertices.get(6));
        graph.addEdge(vertices.get(1), vertices.get(2));
        graph.addEdge(vertices.get(2), vertices.get(6));
        graph.addEdge(vertices.get(2), vertices.get(4));
        graph.addEdge(vertices.get(5), vertices.get(8));

        assertTrue(graph.query(vertices.get(4), vertices.get(6)));
        assertFalse(graph.query(vertices.get(8), vertices.get(6)));

        assertEquals(2, graph.getComponents().size());

        graph.addEdge(vertices.get(5), vertices.get(1));
        assertEquals(1, graph.getComponents().size());
    }
}
