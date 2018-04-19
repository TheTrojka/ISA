import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FanzoneComponent } from './fanzone.component';

describe('FanzoneComponent', () => {
  let component: FanzoneComponent;
  let fixture: ComponentFixture<FanzoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FanzoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FanzoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
