import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HappeningDetailsComponent } from './happening-details.component';

describe('HappeningDetailsComponent', () => {
  let component: HappeningDetailsComponent;
  let fixture: ComponentFixture<HappeningDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HappeningDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HappeningDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
