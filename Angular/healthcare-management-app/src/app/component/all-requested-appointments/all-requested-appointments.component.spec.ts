import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllRequestedAppointmentsComponent } from './all-requested-appointments.component';

describe('AllRequestedAppointmentsComponent', () => {
  let component: AllRequestedAppointmentsComponent;
  let fixture: ComponentFixture<AllRequestedAppointmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllRequestedAppointmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRequestedAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
