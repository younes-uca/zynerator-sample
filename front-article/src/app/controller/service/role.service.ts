import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Pojo } from "../model/pojo";
import { RoleConfig } from "../model/roleConfig";
import { root } from "rxjs/internal-compatibility";
import { PojoService } from "./pojo.service";
import { Menu } from "primeng/menu";
import { MenuRole } from "../model/menuRole";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class RoleService {
  private baseUrl = "http://localhost:8090/";
  private _pojos: Array<Pojo>;
  private _roles: Array<RoleConfig> = new Array<RoleConfig>();
  private _role: RoleConfig = new RoleConfig();
  private _menuRoles: MenuRole[] = new Array<MenuRole>();
  private _selectedRole: RoleConfig = new RoleConfig();
  private _affecterRoleDialog: boolean = false;
  private _addRoleDialog: boolean = false;
  private _editMenuRoleDialog: boolean = false;
  public editMenuRole$ = new BehaviorSubject<boolean>(false);
  public affecterRole$ = new BehaviorSubject<boolean>(false);
  private _menuRoleToBeEdited: MenuRole = new MenuRole();
  public menusHierarchyTree = new Map();

  constructor(private http: HttpClient, pojoService: PojoService) {}
  get menuRoleToBeEdited(): MenuRole {
    return this._menuRoleToBeEdited;
  }
  set menuRoleToBeEdited(value: MenuRole) {
    this._menuRoleToBeEdited = value;
  }
  get editMenuRoleDialog(): boolean {
    return this._editMenuRoleDialog;
  }
  set editMenuRoleDialog(value: boolean) {
    this._editMenuRoleDialog = value;
  }
  get addRoleDialog(): boolean {
    return this._addRoleDialog;
  }
  set addRoleDialog(value: boolean) {
    this._addRoleDialog = value;
  }
  get affecterRoleDialog(): boolean {
    return this._affecterRoleDialog;
  }
  set affecterRoleDialog(value: boolean) {
    this._affecterRoleDialog = value;
  }
  get menuRoles(): MenuRole[] {
    return this._menuRoles;
  }

  set menuRoles(value: MenuRole[]) {
    this._menuRoles = value;
  }

  get selectedRole(): RoleConfig {
    return this._selectedRole;
  }

  set selectedRole(value: RoleConfig) {
    this._selectedRole = value;
  }
  get role(): RoleConfig {
    return this._role;
  }

  set role(value: RoleConfig) {
    this._role = value;
  }
  get pojos(): Array<Pojo> {
    return this.pojos;
  }

  set pojos(value: Array<Pojo>) {
    this.pojos = value;
  }

  get roles(): RoleConfig[] {
    return this._roles;
  }
  set roles(value: Array<RoleConfig>) {
    this._roles = value;
  }
}
