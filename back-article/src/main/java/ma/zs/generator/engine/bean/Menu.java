package ma.zs.generator.engine.bean;

import ma.zs.generator.project.service.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu {
	private long id;
	private String libelle;
	private String icone;
	private String link;
	List<MenuRole> menuRoles;
	private Pojo pojo;
	List<Menu> menuItems;


	public Menu(String libelle, String icone) {
		this.libelle = libelle;
		this.icone = icone;
	}

	public Menu(String libelle) {
		this.libelle = libelle;
	}

	public Menu() {
	}

	public Menu(String libelle, String icone, Pojo pojo,String link) {
		this.libelle = libelle;
		this.icone = icone;
		this.pojo = pojo;
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public List<MenuRole> getMenuRoles() {
		return menuRoles;
	}

	public void setMenuRoles(List<MenuRole> menuRoles) {
		this.menuRoles = menuRoles;
	}

	public Pojo getPojo() {
		return pojo;
	}

	public void setPojo(Pojo pojo) {
		this.pojo = pojo;
	}

	public List<Menu> getMenuItems() {
		if(menuItems==null){
			menuItems= new ArrayList<>();
		}
		return menuItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Menu menu = (Menu) o;
		return id == menu.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void setMenuItems(List<Menu> menuItems) {
		this.menuItems = menuItems;
	}
}
