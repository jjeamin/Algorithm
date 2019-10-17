
class TreeNode {
	public int item;
	public TreeNode left;
	public TreeNode right;
}

public class BST {
	private TreeNode root = new TreeNode();
	
	public BST() {
		this.root = null;
	}
	
	public void search(int item) {
		if(searchBST(this.root, item) == null) {
			System.out.println("[INFO] " + item + " Not Existing");
		}else {
			System.out.println("[INFO] " + item + " Existing");
		}
	}
	
	private TreeNode searchBST(TreeNode B, int item) {
		TreeNode p = B;
		if(p == null) return null;
		else if(item == p.item) return p;
		else if(item < p.item) return searchBST(B.left,item);
		else return searchBST(B.right,item);
	}
	
	
	public void insert(int item) {
		TreeNode p = this.root;
		TreeNode q = new TreeNode();
		
		while(p != null) {
			if(item == p.item) return;
			q = p;
			if(item < p.item) {
				p = p.left;
			}else {
				p = p.right;
			}
		}
		
		TreeNode newNode = new TreeNode();
		newNode.item = item;
		newNode.right = null;
		newNode.left = null;
		
		if(this.root == null) this.root = newNode;
		else if(item < q.item) q.left = newNode;
		else q.right = newNode;
	}

	//����
	public void delete(int item) {
		deleteBST(this.root, item);
	}
	private void deleteBST(TreeNode root, int item) {
		TreeNode p = root;
		TreeNode parent = p;
		TreeNode q = null;
		
		while(p!=null && p.item != item) {	//�ϴ� ����ã��
			if(item == p.item) break;
			parent = p;					
			if(p.item > item) p = p.left;	
			else p = p.right;			
			
		}
		
		if(p == null) return;
		
		if(p.left == null && p.right == null) { //������ 0�� ��带 ����
			if(p == root) this.root = null;
			else if(parent.left == p) parent.left = null; 
			else parent.right = null;				 
		}
		else if(p.left == null || p.right == null) { //������ 1�� ��带 ����
			if(p == root) {
				if(p.left != null) this.root = p.left;
				else this.root = p.right;
			}
			else if(p.left != null) {
				if(parent.left == p) parent.left = p.left;
				else parent.right = p.left;
			}else {
				if(parent.left == p) parent.left = p.right;
				else parent.right = p.right;
			}
		}
		else if(p.left != null && p.right != null) { 
			q = maxNode(p.left);
			p.item = q.item;
			if(p.left == q) p.left = p.left.left;
			else deleteBST(p.left,q.item);
			
		}
		return;
	}
	//�ִ�Ű�� Ž��
	private TreeNode maxNode(TreeNode m) {
		TreeNode p = m;
		
		if(p==null) return p;
		else if(p.right == null) return p;
		else {
			while(p.right != null) {
				p = p.right;
			}
			return p;
		}
	}
	

	public void split(BST bBST, BST cBST,int x) {
		TreeNode Small = new TreeNode();
		TreeNode Large = new TreeNode();
		TreeNode S = Small;
		TreeNode L = Large;
		TreeNode p = this.root;
		
		while(p != null) {
			if(p.item == x) {//���� ���� ������
				S.right = p.left;	//���� �������� ������ �־��ش�
				L.left = p.right;	//������ ū���� ������ �־��ش�
				
				bBST.root = Small.right;	//���� ������� Ʈ���� �־��ش�
				cBST.root = Large.left;		
				
				return;
			}
			else if(p.item > x) {		//ã�� ������ �� ũ�ٸ�
				L.left = p;				//������ �����͸� �ű��
				L = p;					//�� �������� ã�� ����
				p = p.left;
			}
			else {						//ã�� ������ �۴ٸ�
				S.right = p;			//������ �����͸� �ű��
				S = p;					
				p = p.right;
			}
			S.right = null;
			L.left = null;
			bBST.root = Small.right;	
			cBST.root = Large.left;		
		}
		return;
	}
	
	public void show() {
		showBST(this.root);
		System.out.println();
	}
	private void showBST(TreeNode B) {
		if(B != null) {
			System.out.print("[");
			showBST(B.left);
			System.out.print(B.item);
			showBST(B.right);
			System.out.print("]");
		}
	}
}
