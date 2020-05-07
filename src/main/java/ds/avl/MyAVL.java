package ds.avl;

import java.util.ArrayList;

/**
 * @author yyq
 */
public class MyAVL<K extends Comparable<K>,V> {

    private class Node {
        public K key;
        public V val;
        public Node left,right;


        //每个节点都有高度属性,初值为1。
        public int  height;

        public Node(K key, V val){
            this.key=key;
            this.val=val;
            right=null;
            left=null;
            height=1;
        }
    }


    private Node root;
    private int size;
    /**
     * 体会动态生成！！
     */
    public MyAVL(){
        root=null;
        size=0;
    }

    private int getHeight(Node node){
        if(node==null) {
            return 0;
        }
        return node.height;
    }
    private int getBalanceFactor(Node node){
        if(node==null) {
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x=y.left;
        Node T3=x.right;

        x.right=y;
        y.left=T3;

        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x=y.right;
        Node T2=x.left;

        x.left=y;
        y.right=T2;

        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }

    public void add(K key ,V val){
        root=add(root,key,val);
    }

    /**
     *插入过程通过LL,RR,LR,RL维持平衡性
     * @param node 原AVL的根，
     * @param key 键
     * @param val 值
     * @return 插入新元素的后AVL的根。
     */
    private Node add(Node node,K key,V val){
        if(node==null){
            size++;
            return new Node(key,val);
        }
        if(key.compareTo(node.key)<0){
            node.left=add(node.left,key,val);
        }else if(key.compareTo(node.key)>0){
            node.right=add(node.right,key,val);
        }
        else{
            node.val=val;
        }
        node.height=1+Math.max(getHeight(node.left),getHeight(node.right));

        int blc=getBalanceFactor(node);

        //LL
        if(blc>1&&getBalanceFactor(node.left)>=0){
            return rightRotate(node);
        }
        //RR
        if(blc<-1&&getBalanceFactor(node.right)<=0){
            return leftRotate(node);
        }
        //LR
        if(blc>1&&getBalanceFactor(node.left)<0){
            node.left=leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if(blc<-1&&getBalanceFactor(node.right)>0){
            node.right=rightRotate(node.right);
            return leftRotate(node);
        }
        return node;

    }
    public V remove(K key){
        Node node=getNode(root,key);
        if(node!=null){
            root=remove(root,key);
        }
        return null;
    }
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 删除过程需要保证有序性(BST)+平衡性(LL,RR,LR,RL),最复杂
     * @param node 原AVL的根
     * @param key 待删除元素
     * @return 删除key之后新AVL的根
     */
    private Node remove(Node node, K key){

        if(node==null){
            return null;
        }
        Node retNode;
        /**
         * 保证有序性，BST
         */
        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            // return node;
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            // return node;
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                // return rightNode;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                // return leftNode;
                retNode = leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            else{
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                //successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                // return successor;
                retNode = successor;
            }
        }
        /**
         * 平衡性调整，LL,RR,LR,RL和add的检测一致
         * 此时待调整元素为retNode;
         */
        if(retNode==null){
            return null;
        }
        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;

    }


    private Node getNode(Node node,K key){
        if(node==null){
            return null;
        }
        if(key.equals(node.key)){
            return node;
        }else if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }else{
            return getNode(node.right,key);
        }
    }









}
