import {ConfirmationService} from 'primeng/api';
import {MessageService} from 'primeng/api';
import {ProjectConfigService} from 'src/app/controller/service/project-config.service';
import {ProjectConfig} from './../../../../controller/model/project-config';
import {RoleService} from './../../../../controller/service/role.service';
import {Component, OnInit} from '@angular/core';
import {UserConfigService} from '../../../../controller/service/userConfigService';
import {Technology} from '../../../../controller/model/technology';
import {UserConfig} from '../../../../controller/model/userConfig';
import {ProjectTemplate} from '../../../../controller/model/projectTemplate';
import {GeneratedProject} from '../../../../controller/model/generated-project';
import {HttpClient} from '@angular/common/http';
import {PojoService} from '../../../../controller/service/pojo.service';

import {saveAs} from 'file-saver';
import {NgxSpinnerService} from 'ngx-spinner';
import {environment} from '../../../../../environments/environment';

@Component({
    selector: 'app-pojo-generate',
    templateUrl: './pojo-generate.component.html',
    styleUrls: ['./pojo-generate.component.scss'],
    providers: [MessageService, ConfirmationService],
})
export class PojoGenerateComponent implements OnInit {
    showBackendTemplates: boolean = false;
    showFrontendTemplates: boolean = false;
    private url = environment.urlBackEnd;
    private _project: GeneratedProject;

    ngOnInit(): void {
    }

    showProjectStructure: boolean;

    constructor(
        private http: HttpClient,
        private userConfigService: UserConfigService,
        private pojoSerive: PojoService,
        private roleService: RoleService,
        private projectConfigService: ProjectConfigService,
        private messageService: MessageService,
        private spinner: NgxSpinnerService
    ) {
    }

    public generateProject() {
        this.userConfigService.setTechnologiestoGenerate();
        this.userConfigService.userConfig.pojos = this.pojoSerive.items;
        this.userConfigService.userConfig.roles = this.roleService.roles;
        this.userConfigService.userConfig.config.projectName =
            this.projectConfigService.projectName;
        this.userConfigService.userConfig.config.groupId =
            this.projectConfigService.groupId;
        this.userConfigService.userConfig.config.domain =
            this.projectConfigService.domain;

        this.spinner.show();
        this.http
            .post<GeneratedProject>(this.url, this.userConfigService.userConfig)
            .subscribe((response) => {
                this.spinner.hide();

                if (response == null || response.zip == null) {
                    console.log('erreur lors du generation du projet');
                } else {
                    this.messageService.add({severity: 'success', summary: 'Success', detail: 'Project generated successfully'});
                    this.project = response;
                    this.showProjectStructure = true;
                    this.download();
                }
            }, error => {
                this.spinner.hide();
            });
    }

    public download() {
        let blob = new Blob([new Uint8Array(this.project.zip)], {
            type: 'octet/stream',
        });
        saveAs(blob, this.project.name + '.zip');
    }

    get project(): GeneratedProject {
        if (this._project == null) {
            this._project = new GeneratedProject();
        }
        return this._project;
    }

    set project(value: GeneratedProject) {
        this._project = value;
    }

    showTemplatesOfSelectedBackendTechnologie() {
        this.showBackendTemplates = true;
    }

    get backendSelectedTechnology(): Technology {
        return this.userConfigService.backendSelectedTechnology;
    }

    get frontendTechnologies(): Array<Technology> {
        return this.userConfigService.frontendTechnologies;
    }

    get backendTechnologies(): Array<Technology> {
        return this.userConfigService.backendTechnologies;
    }

    get frontendSelectedTechnology(): Technology {
        return this.userConfigService.frontendSelectedTechnology;
    }

    set frontendSelectedTechnology(technology: Technology) {
        this.userConfigService.frontendSelectedTechnology = technology;
    }

    get backendSeletedTechnology(): Technology {
        return this.userConfigService.backendSelectedTechnology;
    }

    set backendSelectedTechnology(technology: Technology) {
        this.userConfigService.backendSelectedTechnology = technology;
    }

    get userConfig(): UserConfig {
        return this.userConfigService.userConfig;
    }

    set userConfig(config: UserConfig) {
        this.userConfigService.userConfig = config;
    }

    changeSelectedBackendTechnology() {
        this.userConfig.backend = this.backendSelectedTechnology.defaultTemplate;
    }

    changeSelectedFrontendTechnology() {
        this.userConfig.frontend = this.frontendSelectedTechnology.defaultTemplate;
    }

    selectBackendTemplate(template: ProjectTemplate) {
        this.userConfig.backend = template;
        this.showBackendTemplates = false;
    }

    selectFrontendTemplate(template: ProjectTemplate) {
        this.userConfig.frontend = template;
        this.showFrontendTemplates = false;
    }

    showTemplatesOfSelectedFrontendTechnologie() {
        this.showFrontendTemplates = true;
    }
}
