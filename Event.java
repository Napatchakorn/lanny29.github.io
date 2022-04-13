package event;

import character.Dragon;
import character.Environment;
import character.Wave;

public class Event {
//	public static boolean checkHit (Dragon dragon,Wave wave) {
//		if(dragon.x+dragon.dragonSize>wave.x && dragon.x < wave.x) {
//			if(dragon.y+dragon.dragonSize>=wave.y-wave.height) {
//				//System.out.println("HIT AT [Y] !!!!!!");
//				return true;
//				
//			}}
//		return false;
//		
//	}
	public static boolean checkHit(Dragon dragon,Wave wave,int dragonSize,int waveHeight){
		if(dragon.x + dragonSize>wave.x && dragon.x <wave.x) {
			if(dragon.y + dragonSize>=wave.y-waveHeight) {
				return true;
			}
		}
		return false;
}

		public static void gameStop(Wave[] wave,Environment[] env) {

		}

}
