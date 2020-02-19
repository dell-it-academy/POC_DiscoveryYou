import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCaracteristicComponent } from './user-caracteristic.component';

describe('UserCaracteristicComponent', () => {
  let component: UserCaracteristicComponent;
  let fixture: ComponentFixture<UserCaracteristicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCaracteristicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCaracteristicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
