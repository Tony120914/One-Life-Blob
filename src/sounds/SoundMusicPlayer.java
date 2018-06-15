package sounds;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class SoundMusicPlayer {
	public static LinkedHashMap<String, Sound> soundLHM = new LinkedHashMap<String, Sound>();
	public static LinkedHashMap<String, Music> musicLHM = new LinkedHashMap<String, Music>();
	
	public static ArrayList<InputStream> streams = new ArrayList<InputStream>();
	public static ArrayList<AudioInputStream> audios = new ArrayList<AudioInputStream>();
	public static LinkedHashMap<String, Clip> clips = new LinkedHashMap<String, Clip>();
	
	public static void loadSoundMusic() {
		try {
			clips.put("background", AudioSystem.getClip());
			clips.put("confirm", AudioSystem.getClip());
			clips.put("confirmEcho", AudioSystem.getClip());
			clips.put("back", AudioSystem.getClip());
			clips.put("quit", AudioSystem.getClip());
			clips.put("death", AudioSystem.getClip());
			clips.put("walking", AudioSystem.getClip());
			
			System.out.println(1);
			clips.get("background").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/Marimba Boy.wav"))));
			System.out.println(2);
			clips.get("confirm").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/confirm_style_4_001.wav"))));
			System.out.println(3);
			clips.get("confirmEcho").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/confirm_style_4_echo_001.wav"))));
			System.out.println(4);
			clips.get("back").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/back_style_4_001.wav"))));
			System.out.println(5);
			clips.get("quit").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/error_style_4_echo_001.wav"))));
			System.out.println(6);
			clips.get("death").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/error_style_5_echo_001.wav"))));
			System.out.println(7);
			clips.get("walking").open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/footstep_dirt.wav"))));
			System.out.println(8);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//
//		
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/Marimba Boy.wav"));
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/footstep_dirt.wav"));
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/confirm_style_4_001.wav"));
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/confirm_style_4_echo_001.wav"));
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/back_style_4_001.wav"));
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/error_style_4_echo_001.wav"));
//		streams.add(SoundMusicPlayer.class.getResourceAsStream("/res_sounds/error_style_5_echo_001.wav"));
//		try {
//			for (int i = 0; i < streams.size(); i++) {
//				System.out.println(1);
//				streams.set(i, new BufferedInputStream(streams.get(i)));
//				System.out.println(2);
//				audios.add(AudioSystem.getAudioInputStream(streams.get(i)));
//				System.out.println(3);
//			}
//		} catch (UnsupportedAudioFileException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			System.out.println(4);
//			clips.put("background", AudioSystem.getClip());
//			clips.put("walking", AudioSystem.getClip());
//			clips.put("confirm", AudioSystem.getClip());
//			clips.put("confirmEcho", AudioSystem.getClip());
//			clips.put("back", AudioSystem.getClip());
//			clips.put("quit", AudioSystem.getClip());
//			clips.put("death", AudioSystem.getClip());
//			System.out.println(5);
//			clips.get("background").open(audios.get(0));
//			clips.get("walking").open(audios.get(1));
//			clips.get("confirm").open(audios.get(2));
//			clips.get("confirmEcho").open(audios.get(3));
//			clips.get("back").open(audios.get(4));
//			clips.get("quit").open(audios.get(5));
//			clips.get("death").open(audios.get(6));
//			System.out.println(6);
//		} catch (LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
		
		//volume control for background
		FloatControl floatControl = (FloatControl) clips.get("background").getControl(FloatControl.Type.MASTER_GAIN);
		floatControl.setValue(-10);
	}
	
	public static Sound getSound(String name) {
		return soundLHM.get(name);
	}
	public static Music getMusic(String name) {
		return musicLHM.get(name);
	}
	
}
