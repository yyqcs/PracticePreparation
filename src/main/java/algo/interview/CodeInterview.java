package algo.interview;

import java.util.*;

class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val=val;
        next=null;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left=null;
        right=null;
    }

}

class RandomListNode{
    int label;
    RandomListNode next;
    RandomListNode random;
    public RandomListNode(int label){
        this.label=label;
        next=null;
        random=null;
    }
}

public class CodeInterview {
    /**
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
     * 每段绳子的长度记为k[1],...,k[m]。
     * 请问k[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，
     * 此时得到的最大乘积是18。
     * @param target 输入一个数n，意义见题面。（2 <= n <= 60）
     * @return 输出答案。
     */
    public int cutRope(int target) {
        int[] dp=new int[target+1];
        dp[1]=1;
        for(int i=2;i<=target;i++){
            for(int j=1;j<i;j++){
                dp[i]=Math.max(dp[i],Math.max(dp[j],j)*(i-j));
            }
        }
        return dp[target];
    }



    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
     * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
     * 请问该机器人能够达到多少个格子？
     * @param threshold k
     * @param rows 行数
     * @param cols 列数
     * @return 格子数
     */
    public int movingCount(int threshold, int rows, int cols)
    {
        int[][]directions={{0,-1},{0,1},{-1,0},{1,0}};
        boolean[][] visited=new boolean[rows][cols];
        floodfill(visited,0,0,rows,cols,threshold,directions);
        return count;

    }
    private int count=0;
    void floodfill(boolean[][] visited,int row,int col,int rows,int cols,int threshold,int[][]directions){
        if(row<0||row>=rows||col<0||col>=cols||visited[row][col]){
            return;
        }
        visited[row][col]=true;
        if(getBitSum(row)+getBitSum(col)>threshold){
            return;
        }
        count++;
        for(int[] direction:directions){
            floodfill(visited,row+direction[0],col+direction[1],
                    rows,cols,threshold,directions);
        }
    }
    public int getBitSum(int x){
        int res=0;
        for(;x!=0;x/=10){
            res+=x%10;
        }
        return res;
    }


    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。窗口大于数组长度的时候，返回空
     * @param num 数组
     * @param size 滑动窗口的大小
     * @return 所有滑动窗口里数值的最大值
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        if(size<=0||size>num.length){
            return new ArrayList<Integer>();

        }
        //单调队列，添加nums[j+1]时，需要将所有比nums[j+1]小的元素删除，然后再添加！！！,联想minStack()
        Deque<Integer> deque=new LinkedList<>();
        ArrayList<Integer> res=new ArrayList<>(num.length-size+1);
        //未形成窗口。
        for(int i=0;i<size;i++){
            while (!deque.isEmpty()&&deque.peekLast()<num[i]){
                deque.removeLast();
            }
            deque.addLast(num[i]);
        }
        res.add(deque.peekFirst());
        //形成窗口后
        for(int i=size;i<num.length;i++){
            if(deque.peekFirst()==num[i-size]){
                deque.removeFirst();
            }
            while (!deque.isEmpty()&&deque.peekLast()<num[i]){
                deque.removeLast();
            }
            deque.addLast(num[i]);
            res.add(deque.peekFirst());
        }
        return res;

    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @param target 整数
     * @param array 二维数组
     * @return 判断数组中是否含有该整数。
     */
    public boolean Find(int target, int [][] array) {
        if(array.length==0){
            return false;
        }
        int row=array.length-1,cols=array[0].length-1;
        int col=0;
        while (row>=0&&col<=cols){
            if(array[row][col]>target){
                row-=1;
            }else if(array[row][col]<target){
                col+=1;
            }else {
                return true;
            }
        }
        return false;

    }


    /**
     * 将一个字符串中的每个空格替换成“%20”
     * @param str 字符串
     * @return 替换后的字符串
     */

    public String replaceSpace(StringBuffer str) {
        int numOfSpace=0;
        int originLength= str.length();
        for(int i=0;i<originLength;i++){
            if(str.charAt(i)==' '){
                numOfSpace++;
            }
        }
        int newLength=originLength+numOfSpace*2;
        str.setLength(newLength);
        for(int i=originLength-1,j=newLength-1;i>=0&&j>=i;i--){
            char c=str.charAt(i);
            if(c==' '){
                str.setCharAt(j--,'0');
                str.setCharAt(j--,'2');
                str.setCharAt(j--,'%');
            }else {
                str.setCharAt(j--,c);
            }
        }
        return str.toString();
    }

    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * @param listNode 链表
     * @return 逆序后的结果。
     */

    private ArrayList<Integer> res=new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode!=null){
            printListFromTailToHead(listNode.next);
            res.add(listNode.val);
        }
        return res;
    }


    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     * @param pre 前序遍历
     * @param in 中序遍历
     * @return 二叉树
     */

    HashMap<Integer,Integer> indexInMiddle=new HashMap<>();
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        for(int i=0;i<in.length;i++){
            indexInMiddle.put(in[i],i);
        }
        return reConstructBinaryTreeRecur(0,0,in.length-1,pre);

    }

    public TreeNode reConstructBinaryTreeRecur(int preRoot,int inLeft,int inRight,int[] pre){
        if(inLeft>inRight){
            return null;
        }
        //每次归位一个元素。root。。根据根在中序遍历的位置，划分左右子树，然后递归！！！
        int rootVal=pre[preRoot];
        TreeNode root=new TreeNode(rootVal);
        int rootIndexInMiddle=indexInMiddle.get(rootVal);
        root.left=reConstructBinaryTreeRecur(preRoot+1,inLeft,rootIndexInMiddle-1,pre);
        root.right=reConstructBinaryTreeRecur(preRoot+rootIndexInMiddle-inLeft+1,rootIndexInMiddle+1,inRight,pre);
        return root;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组[3,4,5,1,2]为[1,2,3,4,5]的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array 非递减排序的数组的一个旋转
     * @return 输出旋转数组的最小元素
     */
    public int minNumberInRotateArray(int [] array) {
        if(array==null||array.length==0){
            return 0;
        }
        //选最小！！！
        int left=0,right=array.length-1;
        while (left<right){
            int mid=left+(right-left)/2;
            if(array[mid]<array[right]){
                right=mid;
            }else if(array[mid]>array[right]){
                left=mid+1;
            }else {
                right--;
            }

        }
        return array[left];

    }


    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项
     * （从0开始，第0项为0，第1项是1）。
     * n<=39
     * @param n 整数n
     * @return 斐波那契数列的第n项
     */
    public int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int f1 = 0, f2 = 1;
        int fn = f1+f2;
        for (int i = 2; i <= n; i++) {
            fn = (f1 + f2)%1000000007;
            f1 = f2;
            f2 = fn;
        }

        return fn;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * @param target n级的台阶
     * @return 跳法总数。
     */
    public int JumpFloor(int target) {
        if(target<=2){
            return target;
        }
        int f1=1,f2=2;
        int fn=f1+f2;

        for(int i=3;i<=target;i++){
            fn=f1+f2;
            f1=f2;
            f2=fn;
        }

        return fn;

    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target n级
     * @return 跳法总数
     */
    public int JumpFloorII(int target) {
        //    f(n) = 2*f(n-1)
        if (target<=0){
            return -1;
        }else if(target==1){
            return 1;
        }
        return 2*JumpFloorII(target-1);

    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * @param target n个2*1的小矩形
     * @return 方法总数
     */
    public int RectCover(int target) {
        if(target<=2){
            return target;
        }
        int f1=1,f2=2;
        int fn=f1+f2;
        for(int i=3;i<=target;i++){
            fn=f1+f2;
            f1=f2;
            f2=fn;
        }
        return fn;
    }
    /**
     * 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
     * @param n 整数
     * @return 1的个数
     */
    public int NumberOf1(int n) {
        int res=0;
        while(n!=0){
            res++;
            n&=(n-1);
        }
        return res;

    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。
     * 求base的exponent次方。 保证base和exponent不同时为0
     * @param base 浮点数base
     * @param exponent int类型的整数exponent
     * @return base的exponent次方
     */
    public double Power(double base, int exponent) {
        if(exponent==0){
            return 1;
        }else if(exponent==1){
            return base;
        }
        boolean isNegitive=false;
        if(exponent<0){
            isNegitive=true;
            exponent= -exponent;
        }
        double res=Power(base,exponent/2);
        res*=res;
        if((exponent&1)==1){
            res*=base;
        }
        return isNegitive?1/res:res;

    }


    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分。
     *
     * (进阶：保证奇数和奇数，偶数和偶数之间的相对位置不变。--需要额外的空间)
     * @param array 整数数组
     */
    public void reOrderArray(int [] array) {
        int left=0,right=array.length-1;
        while (left<right){
            while (left<right&&(array[left]&1)!=0)left++;
            while (left<right&&(array[right]&1)==0) right--;
            if(left<right){
                swap(array,left,right);
            }
        }

    }

    public void swap(int[] nums,int index1,int index2){
        int temp=nums[index1];
        nums[index1]=nums[index2];
        nums[index2]=temp;
    }


    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     * @param head 链表
     * @param k  倒数第k
     * @return 输出该链表中倒数第k个结点。
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head==null||k<=0){
            return null;
        }
        ListNode cur=head;
        for(int i=0;i<k-1;i++){
            if(cur.next==null){
                return null;
            }
            cur=cur.next;
        }
        ListNode res=head;
        while (cur!=null){
            cur=cur.next;
            res=res.next;
        }

        return res;
    }


    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     * @param head 链表
     * @return 反转链表后的表头
     */
    public ListNode ReverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode next= cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;

    }


    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，
     * 当然我们需要合成后的链表满足单调不减规则。
     * @param list1 有序单链表1
     * @param list2 有序单链表2
     * @return 输出两个链表合成后的链表
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }
        if(list1.val< list2.val){
            list1.next=Merge(list1.next,list2);
            return list1;
        }else {
            list2.next=Merge(list1,list2.next);
            return list2;
        }

    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * @param root1 二叉树A
     * @param root2 二叉树B
     * @return 判断B是不是A的子结构
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean res=false;
        if(root1!=null&&root2!=null){
            if(root1.val==root2.val){
                res=containRest(root1,root2);
            }
            if(!res){
                res=HasSubtree(root1.left,root2);
            }
            if(!res){
                res=HasSubtree(root1.right,root2);
            }

        }
        return res;

    }
    public boolean containRest(TreeNode root1,TreeNode root2){

        if(root2==null){
            return true;
        }
        if(root1==null){
            return false;
        }

        if(root1.val!=root2.val){
            return false;
        }
        return containRest(root1.left,root2.left)&&containRest(root1.right,root2.right);

    }


    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * @param root 二叉树的镜像
     */
    public void Mirror(TreeNode root) {
        if (root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            return;
        }
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        if(root.left!=null){
            Mirror(root.left);
        }
        if(root.right!=null){
            Mirror(root.right);
        }
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     * @param matrix  矩阵
     * @return  顺时针的顺序依次打印出每一个数字
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> res=new ArrayList<>();
        if(matrix==null||matrix.length==0){
            return res;
        }
        int top=0,bottom=matrix.length-1,left=0,right=matrix[0].length-1;
        while (true){
            for(int i=left;i<=right;i++){
                res.add(matrix[top][i]);
            }
            if(++top>bottom){
                break;
            }
            for(int i=top;i<=bottom;i++){
                res.add(matrix[i][right]);
            }
            if(left>--right){
                break;
            }
            for(int i=right;i>=left;i--){
                res.add(matrix[bottom][i]);
            }
            if(top>--bottom){
                break;
            }
            for(int i=bottom;i>=top;i--){
                res.add(matrix[i][left]);
            }
            if(++left>right){
                break;
            }

        }

        return res;
    }
//定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。---用另外一个stack记录每个操作的最小值

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）--直到下一个元素，出现在栈顶
     * @param pushA 压入顺序
     * @param popA 可能的弹出顺序
     * @return 弹出顺序是否合法
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> media=new Stack<>();
        int index=0;
        for(int num:pushA){
            media.push(num);
            while (!media.isEmpty()&&media.peek()== popA[index]){
                media.pop();
                index++;
            }
        }
        return media.isEmpty();

    }


    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     * @param root 二叉树
     * @return 打印结果
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        LinkedList<TreeNode> queue=new LinkedList<>();
        ArrayList<Integer> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        queue.addLast(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode cur=queue.removeFirst();
                res.add(cur.val);
                if(cur.left!=null){
                    queue.addLast(cur.left);
                }
                if(cur.right!=null){
                    queue.addLast(cur.right);
                }
            }
        }
        return res;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
     * @param sequence 整数数组
     * @return 某二叉搜索树的后序遍历的结果。
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence==null||sequence.length==0){
            return true;
        }
        return VerifySequenceOfBST(sequence,0,sequence.length-1);

    }
    public boolean VerifySequenceOfBST(int [] sequence,int start,int end) {
        if(start>=end){
            return true;
        }
        int rootVal=sequence[end];
        int index=start;
        while (index<end&&sequence[index]<rootVal){
            index++;
        }
        int leftEnd=index;
        while (index<end&&sequence[index]>=rootVal){
            index++;
        }
        return index==end&&VerifySequenceOfBST(sequence,start,leftEnd-1)&&VerifySequenceOfBST(sequence,leftEnd,end-1);

    }


    /**输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     *
     * @param root 二叉树
     * @param target 和
     * @return 所有路径
     */
    private ArrayList<ArrayList<Integer>> paths=new ArrayList<>();
    private ArrayList<Integer> path=new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        preTraverse(root,target);
        return paths;

    }
    public void preTraverse(TreeNode root,int target){
        if(root==null){
            return;
        }
        target-= root.val;
        path.add(root.val);
        if(root.left==null&&root.right==null&&target==0){
            paths.add(new ArrayList<Integer>(path));
        }
        preTraverse(root.left,target);
        preTraverse(root.right,target);
        //回溯的时候，就需要删除元素！！！
        path.remove(path.get(path.size()-1));
    }


    /**
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
     * 请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     * @param pHead 复杂链表
     * @return 并返回深拷贝后的头结点
     */
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead==null){
            return null;
        }
        RandomListNode cur=pHead;
        while (cur!=null){
            RandomListNode node=new RandomListNode(cur.label);
            node.next=cur.next;
            cur.next=node;
            cur=cur.next.next;//下一个原链接的节点。

        }
        cur=pHead;
        while (cur!=null&&cur.next!=null){
            cur.next.random=cur.random==null?null:cur.random.next;//式中在下一个位置！！！
            cur=cur.next.next;
        }
        RandomListNode head=pHead.next;
        RandomListNode curOldList=pHead;
        RandomListNode curNewList=head;
        while (curOldList!=null){
            curOldList.next=curOldList.next.next;
            curNewList.next=curNewList.next==null?null:curNewList.next.next;
            curOldList=curOldList.next;
            curNewList=curNewList.next;

        }

        return head;

    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * @param pRootOfTree 二叉搜索树
     * @return 排序的双向链表--,收尾节点的处理
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null){
            return null;
        }
        ConvertRecur(pRootOfTree);
        //牛客网提交时需要注销以下两行代码，直接返回this.head.
        this.head.left=pre;
        this.pre.right=head;
        return this.head;
    }
    private TreeNode pre=null;
    private TreeNode head=null;
    public void ConvertRecur(TreeNode root) {
        if(root==null){
            return;
        }
        ConvertRecur(root.left);
        if(this.pre==null){
            head=root;
        }else {
            this.pre.right=root;
        }
        //都会执行这语句。
        root.left=this.pre;
        //记录前一个节点
        pre=root;
        ConvertRecur(root.right);
    }


    /**
     *输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的
     * 所有字符串abc,acb,bac,bca,cab和cba。
     * @param str 字符串
     * @return 排列得到的所有字符串
     */
    public ArrayList<String> Permutation(String str) {
        chars=str.toCharArray();
        Permutation(0);
        permutes.sort(String::compareTo);
        return permutes;
    }
    private ArrayList<String> permutes=new ArrayList<>();
    private char[] chars;
    public void Permutation(int starIndex){
        if(starIndex==chars.length-1){
            permutes.add(String.valueOf(chars));
        }
        Set<Character> set=new HashSet<>();
        for(int i=starIndex;i<chars.length;i++){
            if(set.contains(chars[i])){
                continue;
            }
            set.add(chars[i]);
            //chars[i] 固定在第 starIndex 位
            char temp=chars[i];
            chars[i]=chars[starIndex];
            chars[starIndex]=temp;
            Permutation(starIndex+1);
             temp=chars[i];
            chars[i]=chars[starIndex];
            chars[starIndex]=temp;
        }

    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。
     * 如果不存在则输出0。
     * @param array 数组
     * @return 出现的次数超过数组长度的一半的数字
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        int res=0;
        int count=0;
        for(int num:array){
            if(count==0){
                res=num;
            }
            count+=(res==num)?1:-1;

        }
        count=0;
        for(int num:array ){
            if(num==res){
                count++;
            }
        }
        return count>array.length/2?res:0;
    }


    /**
     *输入n个整数，找出其中最小的K个数。
     * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     * @param input n个整数
     * @param k K
     * @return 其中最小的K个数
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        //首先请做合法性判断
        if(input==null||input.length==0||k<=0||k> input.length){
            return new ArrayList<>(0);
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(k,(a,b)->b.compareTo(a));
        for(int num:input){
            pq.add(num);
            if(pq.size()>k){
                pq.remove();
            }
        }
        return new ArrayList<>(pq);

    }

    public ArrayList<Integer> quickSelect(int[] nums,int left,int right,int k){
        int pivotIndex=partation(nums,left,right);
        if(pivotIndex==k){
            ArrayList<Integer> res=new ArrayList<>();
            for(int i=0;i<=k;i++){
                res.add(nums[i]);
            }
            return res;
        }
        return pivotIndex>k?quickSelect(nums,left,pivotIndex-1,k):quickSelect(nums,pivotIndex+1,right,k);
    }

    private int partation(int[] nums,int left,int right){
        int pivot=nums[left];
        while (left<right){
            while (left<right&&pivot<=nums[right]){
                right--;
            }
            nums[left]=nums[right];
            while (left<right&&nums[left]<=pivot){
                left++;
            }
            nums[right]=nums[left];
        }
        nums[left]=pivot;
        return left;
    }

    /**
     * 要计算连续子向量的最大和
     * @param array 数组
     * @return 连续，最大和。
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int curSum=0;
        int maxVal=Integer.MIN_VALUE;

        for(int num:array){
            if(curSum<0){
                curSum=num;
            }else {
                curSum+=num;
            }
            if(curSum>maxVal){
                maxVal=curSum;
            }

        }
        return maxVal;

    }


    /**
     * 从1到n中1出现的次数
     * @param n n
     * @return 从1 到 n 中1出现的次数
     * 大佬的解法：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int digit=1,res=0;
        int high=n/10,cur=n%10,low=0;
        while (high!=0||cur!=0){
            if(cur==0){
                res+=high*digit;
            }else if(cur==1){
                res+=high*digit+low+1;
            }else {
                res+=(high+1)*digit;
            }
            //移动
            low+=cur*digit;
            cur=high%10;
            high/=10;
            digit*=10;
        }
        return res;

    }

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * @param numbers 输入一个正整数数组
     * @return 所有数字中最小的一个。(x,y)与(y,x).
     */
    public String PrintMinNumber(int [] numbers) {
        if(numbers==null||numbers.length==0){
            return "";
        }
        String[] nums=new String[numbers.length];
        for(int i=0;i<numbers.length;i++){
            nums[i]=String.valueOf(numbers[i]);
        }
        Arrays.sort(nums, (x,y)->(x+y).compareTo(y+x));
        return String.join("",nums);

    }

    /***
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。
     * 求按从小到大的顺序的第N个丑数。
     * @param index N
     * @return 第N个丑数
     */
    public int GetUglyNumber_Solution(int index) {
        if(index<=0){
            return 0;
        }
        int a=1,b=1,c=1;
        int[] dp=new int[index+1];
        dp[1]=1;
        for(int i=2;i<=index;i++){
            int n2=dp[a]*2,n3=dp[b]*3,n5=dp[c]*5;
            dp[i]=Math.min(n2,Math.min(n3,n5));
            if(dp[i]==n2){
                a++;
            }
            if(dp[i]==n3){
                b++;
            }
            if(dp[i]==n5){
                c++;
            }
        }
        return dp[index];
    }

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
     * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
     * @param str 字符串
     * @return 第一个只出现一次的字符的位置
     */
    public int FirstNotRepeatingChar(String str) {
        if(str==null||str.length()==0){
            return -1;
        }
        Map<Character,Integer> dict=new LinkedHashMap<>();
        char[]cs=str.toCharArray();
        for(char c:cs){
            dict.put(c,dict.getOrDefault(c,0)+1);
        }
        char c='A';
        for(Map.Entry<Character,Integer> d:dict.entrySet()){
            if(d.getValue()>1){
                c=d.getKey();
                break;
            }
        }
        for(int i=0;i<cs.length;i++){
            if(cs[i]==c){
                return i;
            }
        }

        return 0;
    }

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。
     * 并将P对1000000007取模的结果输出。 即输出P%1000000007
     * @param array 数组
     * @return 数组中的逆序对的总数P
     */
    public int InversePairs(int [] array) {
        if(array==null||array.length<2){
            return 0;
        }
        int[] aux=new int[array.length];
        InversePairs(array,aux,0,array.length-1);
        return this.pairs;
    }
    private int pairs;
    public void InversePairs(int[] nums,int[]aux,int left,int right){
        if(left<right){

            int mid=left+(right-left)/2;
            InversePairs(nums,aux,left,mid);
            InversePairs(nums,aux,mid+1,right);
            merge(nums,aux,left,mid,right);
        }




    }
    public void merge(int[] nums,int[] aux,int left,int mid,int right) {
        //标准的merge过程，当逆序时，格式为mid-i+1,相当于(mid与i之间的数量)
        int start1 = left, end1 = mid, start2 = mid + 1, end2 = right;
        int k = left;
        while (start1 <= end1 && start2 <= end2) {
            //存在逆序对
            if (nums[start1] > nums[start2]) {
                aux[k++] = nums[start2++];
                this.pairs += (mid - start1 + 1);
                this.pairs %= 1000000007;
            } else {
                aux[k++] = nums[start1++];
            }
        }
        while (start1 <= end1) {
            aux[k++] = nums[start1++];
        }
        while (start2 <= end2) {
            aux[k++] = nums[start2++];
        }
        for (int i = left; i <= right; i++) {
            nums[i] = aux[i];
        }
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。
     * @param pHead1 链表1
     * @param pHead2 链表2
     * @return  第一个公共结点
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null){
            return null;
        }
        ListNode cur1=pHead1,cur2=pHead2;
        while (cur1!=cur2){
            cur1= cur1==null?pHead2:cur1.next;
            cur2=cur2==null?pHead1:cur2.next;
        }
        return cur1;

    }

    /**
     * 统计一个数字在升序数组中出现的次数。
     * @param array 升序数组
     * @param k 数字
     * @return 出现的次数
     */
    public int GetNumberOfK(int [] array , int k) {
        int res=0;
        if(array==null||array.length==0){
            return 0;
        }
        int first=getFirstK(array,k);
        int last=getLastK(array,k);
        if(first>-1&&last>-1){
            res=last-first+1;
        }
        return res;

    }
    public int getFirstK(int[] array,int k){
        int start=0,end=array.length-1;
        while (start<=end){
            int mid=start+(end-start)/2;
            if(array[mid]<k){
                start=mid+1;
            }else if(array[mid]>k){
                end=mid-1;
            }else {
                //相等
                if(mid==0||array[mid-1]<array[mid]){
                    return mid;
                }else {
                    end=mid-1;
                }
            }
        }
        return -1;
    }

    public int getLastK(int[] array,int k){
        int start=0,end=array.length-1;
        while (start<=end){
            int mid=start+(end-start)/2;
            if(array[mid]<k){
                start=mid+1;
            }else if(array[mid]>k){
                end=mid-1;
            }else {
                //相等
                if(mid==array.length-1||array[mid]<array[mid+1]){
                    return mid;
                }else {
                    start=mid+1;
                }
            }
        }
        return -1;
    }

    /**
     * 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
     * 最长路径的长度为树的深度。
     * @param root 一棵二叉树
     * @return 树的深度
     */

    public int TreeDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;

    }

    /**
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * @param root 二叉树
     * @return 判断该二叉树是否是平衡二叉树。
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return BalancedRecur(root)!=-1;


    }
    public  int BalancedRecur(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=BalancedRecur(root.left);
        if(left==-1) return -1;
        int right=BalancedRecur(root.right);
        if(right==-1){
            return -1;
        }
        return Math.abs(left-right)<=1?Math.max(left,right)+1:-1;

    }


    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * @param root BST
     * @param p 节点p
     * @param q 节点q
     * @return 最近公共祖先
     */
    public TreeNode lowestCommonAncestorForBst(TreeNode root,TreeNode p, TreeNode q){
        if(root==null){
            return null;
        }
        if(p.val<root.val&&q.val<root.val){
            return lowestCommonAncestorForBst(root.left,p,q);
        }else if(p.val>root.val&&q.val>root.val){
            return lowestCommonAncestorForBst(root.right,p,q);
        }else{
            return root;
        }

    }


    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * @param root 二叉树
     * @param p 节点p
     * @param q 节点q
     * @return 最近公共祖先
     */
    public TreeNode lowestCommonAncestorForTree(TreeNode root,TreeNode p,TreeNode q){
        if(root==null||root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestorForTree(root.left,p,q);
        TreeNode right=lowestCommonAncestorForTree(root.right,p,q);
        //如果左子树没找到，递归函数返回null,则说明p和q同在root的右侧。那么LCA就是右子树找到的节点
        //当 left 和 right 同时不为空 ,则LCA就是root
        return left == null ? right : right == null ? left : root;
    }

    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * @param array 整型数组
     * @param num1 num1[0]为返回结果
     * @param num2 num2[0]为返回结果
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int diff=0;
        for(int num :array){
            diff^=num;
        }
        // 最右侧不为 0 的位。通过这个位将数组分为两部分，每部分只有一个出现一次的数。
        diff&=-diff;
        for(int num:array){
            if ((diff & num) == 0) {
                num1[0]^=num;
            }else{
                num2[0]^=num;
            }
        }

    }

    /**
     * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，
     * 序列间按照开始数字从小到大的顺序
     * @param sum 和为S
     * @return 连续正数序列
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int start = 1, end = 1, nSum = 0;
        while (start <= sum / 2 ) {
            if (nSum < sum) {
                nSum += end;
                end++;
            } else if (nSum > sum) {
                nSum -= start;
                start++;
            } else {
                ArrayList<Integer> one = new ArrayList<>();
                for (int i = start; i < end; i++) {
                    one.add(i);
                }
                res.add(one);
                nSum -= start;
                start++;
            }
        }
        return res;

    }


    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
     * 使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     * @param array 递增排序的数组
     * @param sum 和
     * @return 两个数，使得他们的和正好是S
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res=new ArrayList<>(2);
        if(array==null||array.length==0||array[0]>sum){
            return res;
        }
        int start=0,end=array.length-1;
        int nSum=0;
        while (start<end){
            nSum=array[start]+array[end];
            if(nSum>sum){
                end--;
            }else if(nSum<sum){
                start++;
            }else{
                //找到一个
                res.add(start);
                res.add(end);
                break;
            }

        }
        return res;

    }


    /**
     * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出
     * @param str 字符序列S
     * @param n 循环左移n位
     * @return 移位后的结果
     */
    public String LeftRotateString(String str,int n) {
        if(str==null||str.length()==0){
            return "";
        }
        int length=str.length();
        char[] chars=str.toCharArray();
        n%=length;
        reverseString(chars,0,n-1);
        reverseString(chars,n,length-1);
        reverseString(chars,0,length-1);
        return new String(chars);

    }

    public void reverseString(char[] chars,int start,int end ){
        while (start<end){
            char ch=chars[start];
            chars[start]=chars[end];
            chars[end]=ch;
            start++;
            end--;
        }
    }


    /**
     * 翻转单词顺序。student. a am I--->I am a student
     * @param str 单词字符串
     * @return 翻转后的字符串
     */
    public String ReverseSentence(String str) {
        if(str.trim().isEmpty()){
            return str;
        }
        String[] strings=str.split(" ");
        StringBuilder sb=new StringBuilder();
        for(int i=strings.length-1;i>=0;i--){
            sb.append(strings[i]);
            if(i!=0){
                sb.append(" ");
            }
        }

        return sb.toString();

    }


    /**
     * 扑克牌顺序
     * @param numbers 扑克牌
     * @return 牌是否能够组成顺子
     */
    public boolean isContinuous(int [] numbers) {
        if(numbers==null||numbers.length==0){
            return false;
        }
        int numsOfQueue=0;
        Arrays.sort(numbers);
        while (numbers[numsOfQueue]==0){
            numsOfQueue++;
        }
        int i=numsOfQueue+1;//左边第2个不为0的元素。
        for(int j=i;j<5;j++){
            int diff=numbers[j]-numbers[j-1];
            numsOfQueue-=(diff-1);//修改
            if(numsOfQueue<0||diff==0){
                return false;
            }
        }
        return true;

    }

        //测试代码
    public static void main(String[] args) {
        int[]nums={4,5,6,7,8,9};

        int size=0;
//        System.out.println(new CodeInterview().maxInWindows(nums,size).toString());
//        System.out.println(new CodeInterview().minNumberInRotateArray(nums));
//        System.out.println(new CodeInterview().Power(2,-3));
//        System.out.println(new CodeInterview().MoreThanHalfNum_Solution(nums));
//        System.out.println(new CodeInterview().InversePairs(nums));
        System.out.println(new CodeInterview().FindNumbersWithSum(nums,11));

    }
//[4,4,6,6,6,5]
//[4, 6, 6, 6, 5]

}
