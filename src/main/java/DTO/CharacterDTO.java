package DTO;

public class CharacterDTO {
	
	private Long id;
	private String name;
	private String weapon;
	private String element;
	private Long level;
	
	public CharacterDTO() {
	}
	
	public CharacterDTO(Long id, String name, String weapon, String element, Long level) {
		super();
		this.id = id;
		this.name = name;
		this.weapon = weapon;
		this.element = element;
		this.level = level;
	}

	public CharacterDTO(String name, String weapon, String element, Long level) {
		super();
		this.name = name;
		this.weapon = weapon;
		this.element = element;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}
	
	

}
