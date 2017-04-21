package processParallelStreamsOfIntegers;

public class Program {

	public static void main(String[] args) {
		FilesReader fr = new FilesReader("D:\\JavaInterviewExercises\\processParallelStreamsOfIntegers\\data\\firstFile.txt");
		fr.read().forEach(i->System.out.println(i));

	}

}
