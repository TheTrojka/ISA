import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFanzoneComponent } from './create-fanzone.component';

describe('CreateFanzoneComponent', () => {
  let component: CreateFanzoneComponent;
  let fixture: ComponentFixture<CreateFanzoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFanzoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFanzoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
