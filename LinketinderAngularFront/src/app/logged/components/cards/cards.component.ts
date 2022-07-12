import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpRequestService, Person } from 'src/app/shared';
import { CardsService } from '../../services';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {

  public loggedPerson:Person;

  public peopleList:Person[]=[];

  public currentCard:Person;

  constructor(private cardsService:CardsService, private router: Router,
    private apiRequest:HttpRequestService) { }

  ngOnInit(): void {
    this.getData()
  }

  async getData(){

    if(!this.apiRequest.loggedPerson){
      this.router.navigate([''])

    }
    this.loggedPerson = await this.cardsService.getLogged()
    this.peopleList = await this.cardsService.getPeopleList(this.loggedPerson.type);

    this.currentCard = this.peopleList.pop()

    console.log(this.peopleList)

  }

  swipe(direction:string){
    if(direction == 'right'){
      this.loggedPerson.approval.push(this.currentCard);
    }
    else{
      this.loggedPerson.disapproval.push(this.currentCard)
    }

    if(this.peopleList.length>0){
      this.currentCard = this.peopleList.pop()
    }
    else{
      this.currentCard.description="No Vacancies available"
      this.currentCard.state=''
      this.currentCard.skills=[]
    }

  }

}
