import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardConnectionComponent } from './card-connection.component';

describe('CardConnectionComponent', () => {
  let component: CardConnectionComponent;
  let fixture: ComponentFixture<CardConnectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardConnectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardConnectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
