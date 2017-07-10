public class Lab1{
	public static void main(String[] args) {
		String file = args[0];
		for(int i = 0; i<file.length(); i++) {
			System.out.println(file.charAt(i));
		}
	}
}