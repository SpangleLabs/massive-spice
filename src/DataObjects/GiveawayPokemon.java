package DataObjects;


public class GiveawayPokemon extends Pokemon {
	private Boolean giveaway_reserved = null;
	private Boolean giveaway_taken = null;
	private String giveaway_player = null;
	private String giveaway_gift = null;
	public Boolean getGiveaway_reserved() {
		return giveaway_reserved;
	}
	public void setGiveaway_reserved(Boolean giveaway_reserved) {
		this.giveaway_reserved = giveaway_reserved;
	}
	public Boolean getGiveaway_taken() {
		return giveaway_taken;
	}
	public void setGiveaway_taken(Boolean giveaway_taken) {
		this.giveaway_taken = giveaway_taken;
	}
	public String getGiveaway_player() {
		return giveaway_player;
	}
	public void setGiveaway_player(String giveaway_player) {
		this.giveaway_player = giveaway_player;
	}
	public String getGiveaway_gift() {
		return giveaway_gift;
	}
	public void setGiveaway_gift(String giveaway_gift) {
		this.giveaway_gift = giveaway_gift;
	}
}
