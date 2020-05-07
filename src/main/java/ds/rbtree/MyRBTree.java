package ds.rbtree;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.NO_IMPLEMENT;

/**
 * @author yyq
 * RBTree需要维护每个节点的颜色属性
 * 类比，AVL树需要维持每个节点的heght属性
 */
public class MyRBTree <K extends Comparable<K>,V> {

    private static final boolean RED=true;
    private static final boolean BLACK=false;

    private class Node{
        public K key;
        public V val;
        public Node left,right;
        public boolean color;

        public Node(K key,V val){
            this.key=key;
            this.val=val;
            left=null;
            right=null;
            // 新生成的节点颜色默认为RED!
            color=RED;
        }
    }

    private Node root;
    private int size;

    /**
     * 体会动态生成！！
     */
    public MyRBTree(){
        root=null;
        size=0;
    }

    /**
     * 当节点为NULL时，颜色为黑、
     * 每个叶子节点(最后的空节点)是黑色的*
     * 根节点为黑色。
     * @param node 判断node的颜色
     * @return {RED,BLACK}
     */
    private boolean isRed(Node node){
        if(node==null){
            return BLACK;
        }
        return node.color;
    }
    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){
        Node x=node.right;

        node.right=x.left;
        x.left=node;

        x.color=node.color;
        node.color=RED;
        return x;
    }
    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){
        //逐个使用
        Node x=node.left;
        // 右旋转
        node.left=x.right;
        x.right=node;

        x.color=node.color;
        node.color=RED;
        return x;
    }
    private void flipColors(Node node){
        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }
    public void add( K key,V val){
        root=add(root,key,val);
        // 最终根节点为黑色节点
        root.color=BLACK;
    }

    /**
     * 加入元素后，依次进行，是否左旋、右旋、颜色翻转判断
     * @param node 原BRT的根
     * @param key 键
     * @param val 值
     * @return 新BRT的根
     */
    private Node add(Node node, K key ,V val){
        if(node==null){
            size++;
            return new Node(key,val);
        }
        if(key.compareTo(node.key)<0){
            node.left=add(node.left,key,val);
        }else if(key.compareTo(node.key)>0){
            node.right=add(node.right,key,val);
        }
        else {
            node.val=val;
        }
        // BRT的性质维护
        if(isRed(node.right)&&!isRed(node.left)){
            node=leftRotate(node);
        }
        if(isRed(node.left)&&isRed(node.left.left)){
            node=rightRotate(node);
        }
        if(isRed(node.left)&&isRed(node.right)){
            flipColors(node);
        }
        return node;
    }




}
