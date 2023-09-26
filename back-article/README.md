# Generator #
in order to generate front
1- modify home.component.ts:
from redirectAdmin(){ this.router.navigate(['/admin/login']); } to :: redirect${roleName.upper}(){ this.router.navigate(['/admin/login']); }

## generate project ##
1- delete content db generator 

2- run POST : http://localhost:8036/generator/backend/{backend}/frontend/{frontend}
 either in post man or in swagger 
(http://localhost:8036/swagger-ui.html ==> generator-rest Generator Rest):

 replace {backend} by the name of the framework that you want for the backend and the same for the frontend and add your yaml file in request body 
 for exemple run  http://localhost:8036/generator/backend/spring/frontend/angular



3- download the file or  go to user folder in folder named "generated"

4-If you want to run the project, create a database named "name of your project" 
( "generated" if you didn't modify the name")
## templates ##
#### how to write the name of a template? ####

- extension can be java or component.ts 

##### case many #####
Dao.all.java.ftl (suffix.all.extension.ftl)
- Dao: suffix of the generated file
- all: template will be generated for every pojo
- java: extension of the generated file 
- ftl: extension of the template
- if you don't want a suffix just start the name with .all.java.ftl

###### case many in folder ######
to generate file in folder for each pojo 

if you want this structure in your generated project 

     - user 
       - user.component.ts 
       - user.component.css
       - user.componnet.html
       - details
          - user-details.component.ts
     - compte 
       - compte.component.ts 
       - compte.component.css
       - compte.componnet.html
       - details
          - compte-details.component.ts
          
you should give templates like this :


   - .all.cpn.component.ts (for user => user.component.ts )   
   - .all.cpn.component.css (for user => user.coponents.css)
   - details.all.details.cpn.component.ts  (for user => details => user-details.component.ts)  
   
the name of the folder inside the folder generated for each pojo should be between all and cpn
for example detail.all.detail.cpn.component.ts will create folder detail inside the folder (user and compte) and  create file with suffix detail 
##### case one #####
application.propreties.ftl  (name.all.extension.ftl)
- application: name for generated file
- propreties: extension of generated file
- ftl for template

#### Technology ####
##### add new one ####
to add a new Technology you should add default template of this technology

