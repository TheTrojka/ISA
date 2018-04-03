import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHappeningComponent } from './create-happening.component';

describe('CreateHappeningComponent', () => {
  let component: CreateHappeningComponent;
  let fixture: ComponentFixture<CreateHappeningComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateHappeningComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateHappeningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
