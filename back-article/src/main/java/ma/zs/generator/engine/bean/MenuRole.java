package ma.zs.generator.engine.bean;

public class MenuRole {
	private long id;
	private Menu menu;
	private RoleConfig role;

	private String ordre;

	public MenuRole(Menu menu, String ordre,RoleConfig role) {
		this.menu = menu;
		this.ordre = ordre;
		this.role=role;
	}

	public MenuRole(String order, String libelle, String icone, String pojoName) {
		if(menu==null){
			menu = new Menu();
		}
		this.ordre = order;
		this.menu.setLibelle(libelle);
		this.menu.setIcone(icone);
		if (this.menu.getPojo() == null) {
			this.menu.setPojo(new Pojo());
		}
		this.menu.getPojo().setName(pojoName);
	}


	public MenuRole() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrdre() {
		return ordre;
	}
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public RoleConfig getRole() {
		return role;
	}

	public void setRole(RoleConfig role) {
		this.role = role;
	}

}
