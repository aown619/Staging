import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';

import { PersonService } from './services/person.service'
import { TileService } from './services/tile.service';
import { UrlService } from './services/url.service';
import { TileComponent } from './tile/tile.component';
import { ReactToInviteComponent } from './invites/react-to-invite/react-to-invite.component';
// import { InviteListComponent } from './invites/invite-list/invite-list.component';
import { InviteComponent } from './invites/invite/invite.component';
import { BoardComponent } from './board/board.component';
import { GamescreenComponent } from './gamescreen/gamescreen.component'
import { NotificationsComponent } from './notifications/notifications.component'
import { InviteService } from './services/invite.service';
import { MatchhistoryComponent } from './matchhistory/matchhistory.component';
import { MatchhistoryService } from './services/matchhistory.service';
import { LoadingComponent } from './loading/loading.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    TileComponent,
    ReactToInviteComponent,
    // InviteListComponent,
    InviteComponent,
    BoardComponent,
    GamescreenComponent,
    NotificationsComponent,
    MatchhistoryComponent,
    LoadingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    PersonService, 
    TileService,
    UrlService,
    InviteService,
    MatchhistoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }