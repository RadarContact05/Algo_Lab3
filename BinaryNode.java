// BinaryNode class; stores a node in a tree.
//
final class BinaryNode<AnyType>
{

    AnyType             element;
    BinaryNode<AnyType> left;
    BinaryNode<AnyType> right;

    public BinaryNode( )
    {
        this( null, null, null );
    }
    
    public BinaryNode( AnyType theElement )
    {
        this( theElement, null, null );
    }
    
    public BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
    {
        element = theElement;
        left    = lt;
        right   = rt;
    }

      // Print tree rooted at current node using preorder traversal.
    public void printPreOrder( )
    {
        System.out.println( element );       // Node
        if( left != null )
            left.printPreOrder( );           // Left
        if( right != null )
            right.printPreOrder( );          // Right
    }


    // Print tree rooted at current node using postorder traversal.
    public void printPostOrder( )
    {
        if( left != null )
            left.printPostOrder( );          // Left
        if( right != null )
            right.printPostOrder( );         // Right
        System.out.println( element );       // Node
    }

    // Print tree rooted at current node using inorder traversal.
    public void printInOrder( )
    {
        if( left != null )
            left.printInOrder( );            // Left
        System.out.println( element );       // Node
        if( right != null )
            right.printInOrder( );           // Right
    }

  /**
     * Return the size of the binary tree rooted at t.
     */
    public static <AnyType> int size( BinaryNode<AnyType> t )
    {
        if( t == null )
            return 0;
        else
            return 1 + size( t.left ) + size( t.right );
    }

    /**
     * Return the height of the binary tree rooted at t.
     */
    public static <AnyType> int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }



    public static void main( String [] arg)
    {
        BinaryNode<String> n1 =new BinaryNode<String> ("Nina", null, null);
        BinaryNode<String> n2 =new BinaryNode<String> ("Kalle", null, null);
        BinaryNode<String> n3 =new BinaryNode<String> ("Peter", n1, n2);
        n3.printPostOrder();
    }


}
