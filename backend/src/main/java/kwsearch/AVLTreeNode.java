package kwsearch;

/**
 * The AVLTreeNode class represents a node in an AVL tree.
 */
public class AVLTreeNode {

    /**
     * The keyword of the node
     */
    String keyword;

    /**
     * The frequency of keyword occurs in the node
     */
    int frequency;

    /**
     * The left & right nodes
     */
    AVLTreeNode left, right;

    /**
     * The height of the node
     */
    int height;

    /**
     * Constructs an AVLTreeNode with the given keyword.
     * Initializes frequency to 1, height to 1, and left and right children to null.
     *
     * @param keyword the keyword stored in the node
     */
    AVLTreeNode(String keyword) {
        this.keyword = keyword;
        this.frequency = 1;
        this.height = 1;
    }
}
