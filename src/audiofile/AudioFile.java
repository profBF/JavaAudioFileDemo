package audiofile;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioFile {
	private String filename;
	AudioInputStream audioStream = null;
	Clip clip;

	public AudioFile(String filename) {  
		this.filename = filename;
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(filename));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Formato audio file non supportato");
		} catch ( LineUnavailableException e ) {
			System.out.println("Uscita audio non presente");
		} catch ( IOException e ) {
			System.out.println("Errore generico di I/O");
		}
	}

	private void showStatus(String msg) {
		System.out.printf("%s: %.2f sec\n", msg, this.getSecondsFromStartPlay());
	}
	
	public void play() {
		clip.start();
		if ( clip.getMicrosecondPosition() == 0 )
			System.out.println("Avvio riproduzione file audio: " + this.filename);
		else	
			showStatus("Ripresa riproduzione file audio " + this.filename + " da ");
	}
	
	public void stop() {
		clip.stop();
		showStatus("Stoppato file audio " + this.filename + " dopo ");
	}
	
	private float getSecondsFromStartPlay() {
		long microsec = clip.getMicrosecondPosition();
		return microsec/1000000F;
	}
	
	public void reset() {
		clip.setMicrosecondPosition(0);
		play();
	}	
	
	public void close() {
		clip.close();
	}
	
}
