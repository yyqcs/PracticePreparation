package ds.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yyq
 */
public class MyBST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;
        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }


    private Node root;
    private int size;
    /**
     * 体会动态生成！！
     */
    public MyBST(){
        root=null;
        size=0;
    }

    public void add(E e){
        root=add(root,e);
    }

    /**
     * 体会BST的生成过程，是动态的
     * @param node：BST的根
     * @param e：等删除插入的元素
     * @return 插入新节点后BST的根。
     */
    private Node add(Node node, E e){
        if(node==null){
            size++;
            return new Node(e);
        }
        //体会有序时，但一直向单侧插入
        if(e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }


    private boolean contains(Node node, E e){
        if(node==null) {
            return  false;
        }
        if(e.compareTo(node.e)==0) {
            return true;
        } else if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }
        else{
            return contains(node.right,e);
        }

    }

    /**
     * BST 的先序遍历，非递归
     */
    public void preOrderNR(){
        if(root==null){
            return ;
        }
        LinkedList<Node> stack=new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node cur=stack.getLast();
            System.out.println(cur.e);
            if(cur.right!=null){
                stack.addLast(cur.right);
            }
            if(cur.left!=null){
                stack.addLast(cur.left);
            }
        }
    }

    /**
     * BST的层次遍历
     * 体会，和非递归的先序遍历的相似性
     */
    public void levelOrder(){
        if(root==null) {
            return ;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur=q.remove();
            System.out.println(cur.e);
            if(cur.left!=null) {
                q.add(cur.left);
            }
            if(cur.right!=null){
                q.add(cur.right);
            }

        }
    }

    public void remove(E e){
        root=remove(root,e);
    }

    /**
     *先查找，然后根据节点是否有左右子树讨论。
     * @param node 原BST的根节点
     * @param e：待删除元素
     * @return :删除节点后新的二分搜索树的根
     */
    private Node remove(Node node,E e){
        if(node==null){
            return null;
        }
        if(e.compareTo(node.e)<0){
            node.left=remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right=remove(node.right,e);
            return node;
        }
        //找到待删除元素了。。
        else{
            if(node.left==null){
                Node rightNode=node.right;
                node.right=null;
                size--;
                return rightNode;
            }
            if(node.right==null){
                Node leftNode=node.left;
                node.left=null;
                size--;
                return leftNode;
            }

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor=minimum(node.right);
            successor.right=removeMin(node.right);
            successor.left=node.left;
            return successor;
        }


    }
    private Node removeMin(Node node){
        if(node.left==null){
            Node rightNode=node.right;
            node.right=null;
            size--;
            return rightNode;
        }
        node.left=removeMin(node.left);
        return node;
    }
    public E minimum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty!");

        }
        return minimum(root).e;
    }
    private Node minimum(Node node){
        if(node.left==null) {
            return node;
        }
        return minimum(node.left);
    }


}
