import {DynamicDialogModule} from "primeng/dynamicdialog";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app-routing.module";

import {AccordionModule} from "primeng/accordion";
import {AutoCompleteModule} from "primeng/autocomplete";
import {AvatarModule} from "primeng/avatar";
import {AvatarGroupModule} from "primeng/avatargroup";
import {BadgeModule} from "primeng/badge";
import {BreadcrumbModule} from "primeng/breadcrumb";
import {ButtonModule} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {CardModule} from "primeng/card";
import {CarouselModule} from "primeng/carousel";
import {CascadeSelectModule} from "primeng/cascadeselect";
import {ChartModule} from "primeng/chart";
import {CheckboxModule} from "primeng/checkbox";
import {ChipModule} from "primeng/chip";
import {ChipsModule} from "primeng/chips";
import {CodeHighlighterModule} from "primeng/codehighlighter";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ConfirmPopupModule} from "primeng/confirmpopup";
import {ColorPickerModule} from "primeng/colorpicker";
import {ContextMenuModule} from "primeng/contextmenu";
import {DataViewModule} from "primeng/dataview";
import {DialogModule} from "primeng/dialog";
import {DividerModule} from "primeng/divider";
import {DropdownModule} from "primeng/dropdown";
import {FieldsetModule} from "primeng/fieldset";
import {FileUploadModule} from "primeng/fileupload";
import {FullCalendarModule} from "primeng/fullcalendar";
import {GalleriaModule} from "primeng/galleria";
import {InplaceModule} from "primeng/inplace";
import {InputNumberModule} from "primeng/inputnumber";
import {InputMaskModule} from "primeng/inputmask";
import {InputSwitchModule} from "primeng/inputswitch";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {KnobModule} from "primeng/knob";
import {LightboxModule} from "primeng/lightbox";
import {ListboxModule} from "primeng/listbox";
import {MegaMenuModule} from "primeng/megamenu";
import {MenuModule} from "primeng/menu";
import {MenubarModule} from "primeng/menubar";
import {MessagesModule} from "primeng/messages";
import {MessageModule} from "primeng/message";
import {MultiSelectModule} from "primeng/multiselect";
import {OrderListModule} from "primeng/orderlist";
import {OrganizationChartModule} from "primeng/organizationchart";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {PaginatorModule} from "primeng/paginator";
import {PanelModule} from "primeng/panel";
import {PanelMenuModule} from "primeng/panelmenu";
import {PasswordModule} from "primeng/password";
import {PickListModule} from "primeng/picklist";
import {ProgressBarModule} from "primeng/progressbar";
import {RadioButtonModule} from "primeng/radiobutton";
import {RatingModule} from "primeng/rating";
import {RippleModule} from "primeng/ripple";
import {ScrollPanelModule} from "primeng/scrollpanel";
import {ScrollTopModule} from "primeng/scrolltop";
import {SelectButtonModule} from "primeng/selectbutton";
import {SidebarModule} from "primeng/sidebar";
import {SkeletonModule} from "primeng/skeleton";
import {SlideMenuModule} from "primeng/slidemenu";
import {SliderModule} from "primeng/slider";
import {SplitButtonModule} from "primeng/splitbutton";
import {SplitterModule} from "primeng/splitter";
import {StepsModule} from "primeng/steps";
import {TabMenuModule} from "primeng/tabmenu";
import {TableModule} from "primeng/table";
import {TabViewModule} from "primeng/tabview";
import {TagModule} from "primeng/tag";
import {TerminalModule} from "primeng/terminal";
import {TieredMenuModule} from "primeng/tieredmenu";
import {TimelineModule} from "primeng/timeline";
import {ToastModule} from "primeng/toast";
import {ToggleButtonModule} from "primeng/togglebutton";
import {ToolbarModule} from "primeng/toolbar";
import {TooltipModule} from "primeng/tooltip";
import {TreeModule} from "primeng/tree";
import {TreeTableModule} from "primeng/treetable";
import {VirtualScrollerModule} from "primeng/virtualscroller";

import {AppComponent} from "./app.component";
import {AppCodeModule} from "./template/app.code.component";
import {AppMainComponent} from "./template/app.main.component";
import {AppConfigComponent} from "./template/app.config.component";
import {AppMenuComponent} from "./menu/app.menu.component";
import {AppMenuitemComponent} from "./menu/app.menuitem.component";
import {AppRightMenuComponent} from "./menu/app.right-menu.component";
import {AppTopBarComponent} from "./menu/app.topbar.component";
import {AppFooterComponent} from "./menu/app.footer.component";
import {MenuService} from "./menu/app.menu.service";
import {ConfirmationService, MessageService, SharedModule} from "primeng/api";
import {PojoListComponent} from "./view/admin/pojos/pojo-list/pojo-list.component";
import {PojoImportComponent} from "./view/admin/pojos/pojo-import/pojo-import.component";
import {PojoGenerateComponent} from "./view/admin/pojos/pojo-generate/pojo-generate.component";
import {CodeEditorModule} from "@ngstack/code-editor";
import {FileLoadComponent} from "./view/admin/pojos/pojo-import/file-load/file-load.component";
import {PojoAddComponent} from "./view/admin/pojos/pojo-add/pojo-add.component";
import {PojoAddFieldComponent} from "./view/admin/pojos/pojo-add-field/pojo-add-field.component";
import {FieldEditComponent} from "./view/admin/pojos/field-edit/field-edit.component";
import {RoleListComponent} from "./view/admin/roles/role-list/role-list.component";
import {RoleAddComponent} from "./view/admin/roles/role-add/role-add.component";
import {RoleEditComponent} from "./view/admin/roles/role-edit/role-edit.component";
import {CreateProjectComponent} from "./view/admin/project/create-project/create-project.component";
import {NgxSpinnerModule} from 'ngx-spinner';

@NgModule({
    imports: [
        BrowserModule,
        InputSwitchModule,
        FormsModule,
        AppRoutingModule,
        SharedModule,
        AppCodeModule,
        HttpClientModule,
        BrowserAnimationsModule,
        CodeEditorModule.forRoot(),
        AccordionModule,
        AutoCompleteModule,
        AvatarModule,
        AvatarGroupModule,
        BadgeModule,
        BreadcrumbModule,
        ButtonModule,
        CalendarModule,
        CardModule,
        CarouselModule,
        CascadeSelectModule,
        ChartModule,
        CheckboxModule,
        ChipModule,
        ChipsModule,
        CodeHighlighterModule,
        ConfirmDialogModule,
        ConfirmPopupModule,
        ColorPickerModule,
        ContextMenuModule,
        DataViewModule,
        DialogModule,
        DividerModule,
        DropdownModule,
        FieldsetModule,
        FileUploadModule,
        FullCalendarModule,
        GalleriaModule,
        InplaceModule,
        InputNumberModule,
        InputMaskModule,
        InputSwitchModule,
        InputTextModule,
        InputTextareaModule,
        KnobModule,
        LightboxModule,
        ListboxModule,
        MegaMenuModule,
        MenuModule,
        MenubarModule,
        MessageModule,
        MessagesModule,
        MultiSelectModule,
        OrderListModule,
        OrganizationChartModule,
        OverlayPanelModule,
        PaginatorModule,
        PanelModule,
        BrowserAnimationsModule,
        PanelMenuModule,
        PasswordModule,
        PickListModule,
        ProgressBarModule,
        RadioButtonModule,
        ReactiveFormsModule,
        RatingModule,
        RippleModule,
        ScrollPanelModule,
        ScrollTopModule,
        SelectButtonModule,
        SidebarModule,
        SkeletonModule,
        SlideMenuModule,
        SliderModule,
        SplitButtonModule,
        SplitterModule,
        StepsModule,
        TableModule,
        TabMenuModule,
        TabViewModule,
        TagModule,
        TerminalModule,
        TimelineModule,
        TieredMenuModule,
        ToastModule,
        ToggleButtonModule,
        ToolbarModule,
        TooltipModule,
        TreeModule,
        TreeTableModule,
        VirtualScrollerModule,
        DynamicDialogModule,
        NgxSpinnerModule
    ],
    declarations: [
        AppComponent,
        AppMainComponent,
        AppMenuComponent,
        AppMenuitemComponent,
        AppConfigComponent,
        AppRightMenuComponent,
        AppTopBarComponent,
        AppFooterComponent,

        PojoListComponent,
        PojoImportComponent,
        PojoGenerateComponent,

        FileLoadComponent,
        PojoAddComponent,
        PojoAddFieldComponent,
        FieldEditComponent,
        RoleListComponent,
        RoleAddComponent,
        RoleEditComponent,
        CreateProjectComponent,
    ],
    providers: [
        MenuService,
        MessageService,
        ConfirmationService,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
