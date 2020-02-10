import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileHeaderComponent } from './main/profile-header/profile-header.component';
import { CardConnectionComponent } from './main/card-connection/card-connection.component';
import { HttpClientModule } from '@angular/common/http';
import { CardDashboardComponent } from './main/card-dashboard/card-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileHeaderComponent,
    CardConnectionComponent,
    CardDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
