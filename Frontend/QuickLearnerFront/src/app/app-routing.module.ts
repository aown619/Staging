import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { GamescreenComponent} from './gamescreen/gamescreen.component';
import { MatchhistoryComponent } from './matchhistory/matchhistory.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path: 'gamescreen',
    component: GamescreenComponent
  },
  {
    path: 'matchhistory',
    component: MatchhistoryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
