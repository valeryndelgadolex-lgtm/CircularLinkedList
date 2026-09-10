public class MiListaCircular implements ListInterface {

    Node cabeza;

    @Override
    public boolean isEmpty() {
        return this.cabeza == null;
    }

    @Override
    public int getSize() {

        if (this.cabeza == null) {
            return 0;
        }

        Node iterador = this.cabeza;
        int contador = 0;

        while (true) {

            contador = contador + 1;
            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return contador;
    }

    @Override
    public void clear() {
        this.cabeza = null;
    }

    @Override
    public Object getHead() {

        if (this.cabeza == null) {
            return null;
        }

        return this.cabeza.dato;
    }

    @Override
    public Object getTail() {

        if (this.cabeza == null) {
            return null;
        }

        Node iterador = this.cabeza;

        while (iterador.siguiente != this.cabeza) {
            iterador = iterador.siguiente;
        }

        return iterador.dato;
    }

    @Override
    public Object get(Node node) {

        if (this.cabeza == null) {
            return null;
        }

        Node iterador = this.cabeza;

        while (true) {

            if (iterador == node) {
                return iterador.dato;
            }

            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return null;
    }

    @Override
    public Node search(Object object) {

        if (this.cabeza == null) {
            return null;
        }

        Node iterador = this.cabeza;

        while (true) {

            if (iterador.dato.equals(object)) {
                return iterador;
            }

            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return null;
    }

    @Override
    public boolean add(Object object) {
        insertTail(object);
        return true;
    }

    @Override
    public boolean insert(Node node, Object object) {

        if (this.cabeza == null || node == null) {
            return false;
        }

        Node nuevoNodo = new Node(object);

        nuevoNodo.siguiente = node.siguiente;
        node.siguiente = nuevoNodo;

        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {

        if (this.cabeza == null) {
            return false;
        }

        Node iterador = this.cabeza;

        while (true) {

            if (iterador.dato.equals(objectRef)) {

                Node nuevoNodo = new Node(object);

                nuevoNodo.siguiente = iterador.siguiente;
                iterador.siguiente = nuevoNodo;

                return true;
            }

            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return false;
    }

    @Override
    public boolean insertHead(Object object) {

        Node nuevoNodo = new Node(object);

        if (this.cabeza == null) {

            this.cabeza = nuevoNodo;
            nuevoNodo.siguiente = this.cabeza;

            return true;
        }

        Node ultimo = this.cabeza;

        while (ultimo.siguiente != this.cabeza) {
            ultimo = ultimo.siguiente;
        }

        nuevoNodo.siguiente = this.cabeza;
        this.cabeza = nuevoNodo;
        ultimo.siguiente = this.cabeza;

        return true;
    }

    @Override
    public boolean insertTail(Object object) {

        Node nuevoNodo = new Node(object);

        if (this.cabeza == null) {

            this.cabeza = nuevoNodo;
            nuevoNodo.siguiente = this.cabeza;

            return true;
        }

        Node iterador = this.cabeza;

        while (iterador.siguiente != this.cabeza) {
            iterador = iterador.siguiente;
        }

        iterador.siguiente = nuevoNodo;
        nuevoNodo.siguiente = this.cabeza;

        return true;
    }

    @Override
    public boolean set(Node node, Object object) {

        if (this.cabeza == null) {
            return false;
        }

        Node iterador = this.cabeza;

        while (true) {

            if (iterador == node) {
                iterador.dato = object;
                return true;
            }

            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return false;
    }

    @Override
    public boolean remove(Node node) {

        if (this.cabeza == null || node == null) {
            return false;
        }

        // Cuando solo hay un nodo
        if (this.cabeza == node && this.cabeza.siguiente == this.cabeza) {
            this.cabeza = null;
            return true;
        }

        // Cuando eliminamos la cabeza
        if (this.cabeza == node) {

            Node ultimo = this.cabeza;

            while (ultimo.siguiente != this.cabeza) {
                ultimo = ultimo.siguiente;
            }

            this.cabeza = this.cabeza.siguiente;
            ultimo.siguiente = this.cabeza;

            return true;
        }

        Node anterior = this.cabeza;
        Node iterador = this.cabeza.siguiente;

        while (iterador != this.cabeza) {

            if (iterador == node) {
                anterior.siguiente = iterador.siguiente;
                return true;
            }

            anterior = anterior.siguiente;
            iterador = iterador.siguiente;
        }

        return false;
    }

    @Override
    public boolean contains(Object object) {

        if (this.cabeza == null) {
            return false;
        }

        Node iterador = this.cabeza;

        while (true) {

            if (iterador.dato.equals(object)) {
                return true;
            }

            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return false;
    }

    @Override
    public Object[] toArray() {

        int longitudd = getSize();
        Object[] array = new Object[longitudd];
        if (this.cabeza == null) {
            return array;
        }
        Node iterador = this.cabeza;
        int indice = 0;
        while (true) {
            array[indice] = iterador.dato;
            indice = indice + 1;
            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }

        return array;
    }

    @Override
    public Object[] toArray(Object[] object) {
        int longitud = getSize();
        if (object.length < longitud) {
            object = new Object[longitud];
        }
        if (this.cabeza == null) {
            return object;
        }
        Node iterador = this.cabeza;
        int indice = 0;
        while (true) {
            object[indice] = iterador.dato;
            indice = indice + 1;
            iterador = iterador.siguiente;

            if (iterador == this.cabeza) {
                break;
            }
        }
        return object;
    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        MiListaCircular sublista = new MiListaCircular();
        if (this.cabeza == null) {
            return sublista;
        }
        Node iterador = this.cabeza;
        while (true) {
            if (iterador == from) {
                while (true) {
                    sublista.insertTail(iterador.dato);
                    if (iterador == to) {
                        return sublista;
                    }
                    iterador = iterador.siguiente;
                }
            }
            iterador = iterador.siguiente;
            if (iterador == this.cabeza) {
                break;
            }
        }
        return sublista;
    }

    @Override
    public MiListaCircular sortList() {

        if (this.cabeza == null) {
            return this;
        }

        Node actual = this.cabeza;

        while (true) {

            Node menor = actual;
            Node iterador = actual.siguiente;

            while (iterador != this.cabeza) {

                if (((Comparable) iterador.dato).compareTo(menor.dato) < 0) {
                    menor = iterador;
                }

                iterador = iterador.siguiente;
            }

            Object temporal = actual.dato;
            actual.dato = menor.dato;
            menor.dato = temporal;

            actual = actual.siguiente;

            if (actual == this.cabeza) {
                break;
            }
        }

        return this;
    }
}
