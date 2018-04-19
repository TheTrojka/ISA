import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropsDetailsComponent } from './props-details.component';

describe('PropsDetailsComponent', () => {
  let component: PropsDetailsComponent;
  let fixture: ComponentFixture<PropsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
