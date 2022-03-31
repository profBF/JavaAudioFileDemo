package audiofile;

import java.util.Scanner;

public class TestAudioFile {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AudioFile au = new AudioFile("go_down_swinging.wav");
		String input = "";
		
		while (! input.equals("Q") ) {
			System.out.println("P = play, S = stop, R = reset, Q = quit");
			System.out.print("Scelta: ");
			input = scanner.next();
			input = input.toUpperCase();
			switch ( input ) {
			case "P":
				au.play();
				break;
			case "S":
				au.stop();
				break;
			case "R":
				au.reset();
				break;
			case "Q":
				au.close();
				break;
			}
		}
		System.out.print("Ciaooo...");
	}

}
