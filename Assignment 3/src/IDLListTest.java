//Name: Gundeep Singh Saluja
//CWID: 20005427

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> new_list= new IDLList<Integer>();
		new_list.add(34);
		new_list.add(21);
		new_list.add(40);
		new_list.add(82);
		new_list.add(13);
		new_list.add(2, 37);
		new_list.append(67);
		new_list.append(90);
        System.out.println(new_list.toString());
        System.out.println(new_list.get(2));
        System.out.println(new_list.getHead());
        System.out.println(new_list.getLast());
        System.out.println(new_list.size());
        System.out.println(new_list.remove());
        System.out.println(new_list.toString());
        System.out.println(new_list.removeLast());
        System.out.println(new_list.toString());
        System.out.println(new_list.removeAt(4));
        System.out.println(new_list.toString());
        System.out.println(new_list.remove());
        System.out.println(new_list.toString());
    }
}