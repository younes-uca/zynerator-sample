import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Pojo } from "../model/pojo";
import { Observable, Subject } from "rxjs";
import { Field } from "../model/field";
import { RequestVo } from "../model/request-vo.model";
import { Permission } from "../model/permission";
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: "root",
})
export class PojoService {
  private urlGenerator = environment.urlBackEnd;
  private url = this.urlGenerator + "style/yaml";
  private _items: Array<Pojo>;
  private _selected: Pojo;
  private _selectes: Array<Pojo>;
  private _pojo: Pojo;
  private _addDialog;
  editField$: Subject<boolean> = new Subject<boolean>();
  private _createDialog: boolean;
  private _editDialog: boolean;
  private _viewDialog: boolean;
  private _submitted: boolean;
  private _addFieldToExistingPojoDialog: boolean;
  private _selectedPojoToBeEdited: Pojo;
  private _editFieldDialog: boolean;
  private _fieldToBeEdited: Field = new Field();
  private _fieldToBeEditedIndex: number = 0;
  private _requestVo: RequestVo;

  // constructor(private messageService: MessageService,
  //             private confirmationService: ConfirmationService, private http: HttpClient) {
  // }
  constructor(private http: HttpClient) {}

  public generate(): Observable<Array<Pojo>> {
    return this.http.post<any>(this.urlGenerator, this.requestVo.userConfig);
  }
  public importYaml(): Observable<Array<Pojo>> {
    return this.http.post<any>(this.url + "/convert/", this.requestVo);
  }

  public findAll(): Observable<Array<Pojo>> {
    return this.http.get<Array<Pojo>>(this.url);
  }

  public save(): Observable<Pojo> {
    return this.http.post<Pojo>(this.url, this.selected);
  }

  public edit(): Observable<Pojo> {
    return this.http.put<Pojo>(this.url, this.selected);
  }

  // public deleteByReference(){
  //   return this.http.delete<number>(this.url + 'reference/' + this.selected.reference);
  //}

  /* public deleteMultipleByReference(): Observable<number> {
       return this.http.post<number>(this.url + 'delete-multiple-by-reference' , this.selectes);
     }*/

  public findIndexById(id: Field): number {
    let index = -1;
    for (let i = 0; i < this.items.length; i++) {
      if (this.items[i].id === id) {
        index = i;
        break;
      }
    }
    return index;
  }

  public deleteIndexById(id: Field) {
    this.items.splice(this.findIndexById(id), 1);
  }
  public fillojoPermitions() {
    this.items.forEach((pojo) => {
      pojo.permissions = pojo.permissions || [];

      pojo.permissions.push({ name: pojo.name + ".edit" });
      pojo.permissions.push({ name: pojo.name + ".list" });
      pojo.permissions.push({ name: pojo.name + ".view" });
      pojo.permissions.push({ name: pojo.name + ".add" });
      pojo.permissions.push({ name: pojo.name + ".delete" });
    });
  }

  public deleteMultipleIndexById() {
    for (const item of this.selectes) {
      this.deleteIndexById(item.id);
    }
  }
  get pojo(): Pojo {
    if (this._pojo == null) {
      this._pojo = new Pojo();
    }
    return this._pojo;
  }

  set pojo(value: Pojo) {
    this._pojo = value;
  }

  get requestVo(): RequestVo {
    if (this._requestVo == null) {
      this._requestVo = new RequestVo();
    }
    return this._requestVo;
  }

  set requestVo(value: RequestVo) {
    this._requestVo = value;
  }

  get items(): Array<Pojo> {
    return this._items;
  }

  set items(value: Array<Pojo>) {
    this._items = value;
  }

  get selected(): Pojo {
    if (this._selected == null) {
      this._selected = new Pojo();
    }
    return this._selected;
  }

  set selected(value: Pojo) {
    this._selected = value;
  }

  get selectes(): Array<Pojo> {
    return this._selectes;
  }

  set selectes(value: Array<Pojo>) {
    this._selectes = value;
  }

  get createDialog(): boolean {
    return this._createDialog;
  }

  set createDialog(value: boolean) {
    this._createDialog = value;
  }

  get editDialog(): boolean {
    return this._editDialog;
  }

  set addFieldToExistingPojoDialog(value: boolean) {
    this._addFieldToExistingPojoDialog = value;
  }

  get addFieldToExistingPojoDialog(): boolean {
    return this._addFieldToExistingPojoDialog;
  }

  set selectedPojoToBeEdited(value: Pojo) {
    this._selectedPojoToBeEdited = value;
  }

  get selectedPojoToBeEdited(): Pojo {
    return this._selectedPojoToBeEdited;
  }

  set editDialog(value: boolean) {
    this._editDialog = value;
  }

  get submitted(): boolean {
    return this._submitted;
  }

  set submitted(value: boolean) {
    this._submitted = value;
  }

  get viewDialog(): boolean {
    return this._viewDialog;
  }

  set viewDialog(value: boolean) {
    this._viewDialog = value;
  }
  get addDialog(): boolean {
    return this._addDialog;
  }

  set addDialog(value: boolean) {
    this._addDialog = value;
  }
  get editFieldDialog(): boolean {
    return this._editFieldDialog;
  }

  set editFieldDialog(value: boolean) {
    this._editFieldDialog = value;
  }
  get fieldToBeEdited(): Field {
    return this._fieldToBeEdited;
  }

  set fieldToBeEdited(value: Field) {
    this._fieldToBeEdited = value;
  }
  get fieldToBeEditedIndex(): number {
    return this._fieldToBeEditedIndex;
  }

  set fieldToBeEditedIndex(value: number) {
    this._fieldToBeEditedIndex = value;
  }
}
