package processParallelStreamsOfIntegers;

public class Program {

	public static void main(String[] args) {
		FilesReader fr = new FilesReader("D:\\JavaInterviewExercises\\processParallelStreamsOfIntegers\\data\\firstFile.txt");
		fr.start();
	//	fr.read().forEach(i->System.out.println(i));
		try {
			fr.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fr.getList().forEach(i->System.out.println(i));
	}

}
