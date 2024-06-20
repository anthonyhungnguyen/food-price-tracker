package kwsearch;

import com.scraper.api.model.KeywordSearchData;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The AVLTree class represents an AVL tree, which is a self-balancing binary search tree.
 * It provides methods to insert keywords, balance the tree, and print the top K
 * most frequent keywords.
 */
public class AVLTree {
    /**
     * The AVLTree root node.
     */
    private AVLTreeNode root;

    /**
     * Inserts a keyword into the AVL tree.
     *
     * @param keyword the keyword to be inserted
     */
    public void insert(String keyword) {
        root = insert(root, keyword);
    }

    /**
     * Adds a keyword to the AVL tree with the given node as the root.
     *
     * @param avlTreeNode    the root node of the subtree
     * @param keyword the keyword to be inserted
     * @return the new root of the subtree
     */
    private AVLTreeNode insert(AVLTreeNode avlTreeNode, String keyword) {
        if (avlTreeNode == null) return new AVLTreeNode(keyword);

        if (keyword.equals(avlTreeNode.keyword)) {
            avlTreeNode.frequency++;
        } else if (keyword.compareTo(avlTreeNode.keyword) < 0) {
            avlTreeNode.left = insert(avlTreeNode.left, keyword);
        } else {
            avlTreeNode.right = insert(avlTreeNode.right, keyword);
        }

        avlTreeNode.height = 1 + Math.max(height(avlTreeNode.left), height(avlTreeNode.right));
        return balance(avlTreeNode);
    }

    /**
     * Returns the height of the given node.
     *
     * @param avlTreeNode the node whose height is to be calculated
     * @return the height of the node
     */
    private int height(AVLTreeNode avlTreeNode) {
        return avlTreeNode == null ? 0 : avlTreeNode.height;
    }

    /**
     * Returns the balance factor of the given node.
     *
     * @param avlTreeNode the node whose balance factor is to be calculated
     * @return the balance factor of the node
     */
    private int getBalance(AVLTreeNode avlTreeNode) {
        return avlTreeNode == null ? 0 : height(avlTreeNode.left) - height(avlTreeNode.right);
    }

    /**
     * Performs a right rotation on the given node.
     *
     * @param avlTreeNode the node to be rotated
     * @return the new root of the subtree after rotation
     */
    private AVLTreeNode rotateRight(AVLTreeNode avlTreeNode) {
        AVLTreeNode x = avlTreeNode.left;
        AVLTreeNode T2 = x.right;
        x.right = avlTreeNode;
        avlTreeNode.left = T2;
        avlTreeNode.height = Math.max(height(avlTreeNode.left), height(avlTreeNode.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    /**
     * Performs a left rotation on the given node.
     *
     * @param avlTreeNode the node to be rotated
     * @return the new root of the subtree after rotation
     */
    private AVLTreeNode rotateLeft(AVLTreeNode avlTreeNode) {
        AVLTreeNode y = avlTreeNode.right;
        AVLTreeNode T2 = y.left;
        y.left = avlTreeNode;
        avlTreeNode.right = T2;
        avlTreeNode.height = Math.max(height(avlTreeNode.left), height(avlTreeNode.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    /**
     * Balances the given node.
     *
     * @param avlTreeNode the node to be balanced
     * @return the new root of the subtree after balancing
     */
    private AVLTreeNode balance(AVLTreeNode avlTreeNode) {
        int balance = getBalance(avlTreeNode);
        if (balance > 1) {
            if (getBalance(avlTreeNode.left) < 0) avlTreeNode.left = rotateLeft(avlTreeNode.left);
            return rotateRight(avlTreeNode);
        }
        if (balance < -1) {
            if (getBalance(avlTreeNode.right) > 0) avlTreeNode.right = rotateRight(avlTreeNode.right);
            return rotateLeft(avlTreeNode);
        }
        return avlTreeNode;
    }

    /**
     * Performs an in-order traversal of the subtree rooted at the given node.
     * Adds each node to the max heap.
     *
     * @param avlTreeNode    the root of the subtree
     * @param maxHeap the max heap to which nodes are added
     */
    private void inOrderTraversal(AVLTreeNode avlTreeNode, PriorityQueue<AVLTreeNode> maxHeap) {
        if (avlTreeNode != null) {
            inOrderTraversal(avlTreeNode.left, maxHeap);
            maxHeap.add(avlTreeNode);
            inOrderTraversal(avlTreeNode.right, maxHeap);
        }
    }

    /**
     * Prints the top K most frequent keywords in the AVL tree.
     *
     * @param k the number of top frequent keywords to print
     */
    public void printTopK(int k) {
        PriorityQueue<AVLTreeNode> maxHeap = new PriorityQueue<>((x, y) -> y.frequency - x.frequency);
        inOrderTraversal(root, maxHeap);

        for (int j = 0; j < k && !maxHeap.isEmpty(); j++) {
            AVLTreeNode node = maxHeap.poll();
            System.out.println("[" + node.frequency + "]  " + node.keyword);
        }
    }


    /**
     * Prints the top K most frequent keywords in the AVL tree.
     *
     * @param k the number of top frequent keywords to print
     */
    public Set<KeywordSearchData> getTopK(int k) {
        Set<KeywordSearchData> response = new HashSet<>();
        PriorityQueue<AVLTreeNode> maxHeap = new PriorityQueue<>((x, y) -> y.frequency - x.frequency);
        inOrderTraversal(root, maxHeap);

        for (int j = 0; j < k && !maxHeap.isEmpty(); j++) {
            AVLTreeNode node = maxHeap.poll();
            KeywordSearchData kwData = new KeywordSearchData();
            kwData.setKeyword(node.keyword);
            kwData.setCount(node.frequency);
            kwData.setSearchTime(LocalDateTime.now().toString());
            response.add(kwData);
        }
        return response;
    }
}
