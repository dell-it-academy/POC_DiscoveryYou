import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardCaracteristicComponent } from './card-caracteristic.component';

describe('CardCaracteristicComponent', () => {
  let component: CardCaracteristicComponent;
  let fixture: ComponentFixture<CardCaracteristicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardCaracteristicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardCaracteristicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
