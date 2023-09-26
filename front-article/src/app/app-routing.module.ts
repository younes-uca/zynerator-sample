import { RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";

import { AppMainComponent } from "./template/app.main.component";
import { PojoListComponent } from "./view/admin/pojos/pojo-list/pojo-list.component";
import { PojoImportComponent } from "./view/admin/pojos/pojo-import/pojo-import.component";
import { PojoGenerateComponent } from "./view/admin/pojos/pojo-generate/pojo-generate.component";
import { RoleListComponent } from "./view/admin/roles/role-list/role-list.component";
import { CreateProjectComponent } from "./view/admin/project/create-project/create-project.component";

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: "",
          component: AppMainComponent,
          children: [
            { path: "", component: CreateProjectComponent },
            { path: "view/pojo/load", component: PojoImportComponent },
            { path: "view/pojo/show", component: PojoListComponent },
            { path: "view/pojo/generate", component: PojoGenerateComponent },
            { path: "create", component: CreateProjectComponent },
            { path: "view/role/show", component: RoleListComponent },

          ],
        },
        { path: "**", redirectTo: "/404" },
      ],
      { scrollPositionRestoration: "enabled" }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
