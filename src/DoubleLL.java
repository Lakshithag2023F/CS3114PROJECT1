public class DoubleLL {
    //~ Fields ................................................................
    private int size;

    //~ Constructors ..........................................................
    public DoubleLL()
    {
        init();
    }
    //~Public  Methods ........................................................
    private void init()
    {
        
        size = 0;
    }
    
    
    /**
     * Sets the node before this one
     *
     * @param n
     *            the node before this one
     */
    public void setPrevious(Node<E> n)
    {
        previous = n;
    }

}
