import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePropsAdComponent } from './create-props-ad.component';

describe('CreatePropsAdComponent', () => {
  let component: CreatePropsAdComponent;
  let fixture: ComponentFixture<CreatePropsAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePropsAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePropsAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
