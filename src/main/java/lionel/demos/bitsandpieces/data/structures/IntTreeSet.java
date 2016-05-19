
package lionel.demos.bitsandpieces.data.structures;

/**
 * @author lionel.ngounou
 */
public class IntTreeSet implements IntSet{
	
	private final int value, level; 
	private IntTreeSet left, right; 	

	public IntTreeSet(int value) {
		this(value, 0);
	}
	
	private IntTreeSet(int value, int level) {
		this.value = value;
		this.level = level;
	}
	
	@Override
	public boolean contains(int value){
		return this.value==value || (left!=null && left.contains(value)) 
				|| (right!=null && right.contains(value)); 
	}
	
	@Override
	public boolean add(int value){
		if(!contains(value)){
			IntTreeSet next = findNextToFill();
			if (next != null) {
				if (next.left == null) {
					next.left = new IntTreeSet(value, next.level + 1);
				} else {
					next.right = new IntTreeSet(value, next.level + 1);
				}
				return true;
			}
		}
		return false;
	}
	
	private IntTreeSet findNextToFill(){
		IntTreeSet next = null; 
		int l = level;
		do {			
			next = findUnfilled(l++);
		} while (next==null);
		return next;
	}
	
	private IntTreeSet findUnfilled(int level){
		if(level<this.level)
			return null;
		if(level==this.level && !isFilled())
			return this;		
		IntTreeSet s = left.findUnfilled(level);
		if(s!=null) 
			return s;
		return right.findUnfilled(level);
	}
	
	private boolean isFilled(){
		return left!=null && right!=null;
	}	
	
	public void print(){
		Integer lv = left!=null? left.value : null;
		Integer rv = right!=null? right.value : null;
		System.out.println(value + " ::: L<-"+lv + " R->"+rv);
		
		if(left!=null) left.print();		
		if(right!=null) right.print();
	}
	
	public static void main(String[] args) {
		IntTreeSet set = new IntTreeSet(2);
		set.add(4); 
		set.add(2); 
		set.add(5); 
		set.add(15); 
		set.add(53); 
		set.add(3); 
		set.add(1); 
		set.add(9); 
		set.add(6); 
		set.add(7);
		set.add(7); 
		set.add(17);
		set.print();
	}
}
