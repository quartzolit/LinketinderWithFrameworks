import { Component, OnInit } from '@angular/core';
import { HttpRequestService, Person } from 'src/app/shared';
import { SkillsService } from '../../services';
import { Router } from '@angular/router';
import { faCircleMinus , faCirclePlus} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {
  public loggedPerson:Person;
  //fcm = faCircleMinus;
  //fcp = faCirclePlus;
  public removeSkill:string = 'select-one';
  public addSkill:string = 'select-one';
  public level:string ='None';

   
  constructor(private skillService:SkillsService, private apiRequest:HttpRequestService, private router:Router) { }

  ngOnInit(): void {
    this.getData()
   
  }
  ngOnDestroy():void{
    this.update();
  }

  getData(){

    if(!this.apiRequest.loggedPerson){
      this.router.navigate([''])

    }
    this.loggedPerson = this.apiRequest.loggedPerson;
  }

  addSkillOnList(){
    this.loggedPerson = this.skillService.addAndUpdate(this.addSkill, this.loggedPerson, this.level)
    this.addSkill= 'select-one';

  }

  removeSkillOnList(){

    console.log(this.removeSkill)
    this.loggedPerson = this.skillService.removeAndUpdate(this.removeSkill, this.loggedPerson)

    this.removeSkill= 'select-one';

  }

  update(){
    this.skillService.update(this.loggedPerson)

  }

}
