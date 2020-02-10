import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileHeaderComponent } from './main/profile-header/profile-header.component';
import { CardConnectionComponent } from './main/card-connection/card-connection.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileHeaderComponent,
    CardConnectionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
