package org.gapi.common;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 7/8/17
 */
public class Vertex<T> {

    private T wrappedObj;

    public Vertex(T _wrappedObj) {
        wrappedObj = _wrappedObj;
    }

    public String getId() {
        return "Vtx:" + wrappedObj.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        return (obj instanceof Vertex) && (this.getId().equals(((Vertex) obj).getId()));
    }
}
