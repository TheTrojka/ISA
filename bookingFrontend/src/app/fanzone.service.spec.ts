import { TestBed, inject } from '@angular/core/testing';

import { FanzoneService } from './fanzone.service';

describe('FanzoneService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FanzoneService]
    });
  });

  it('should be created', inject([FanzoneService], (service: FanzoneService) => {
    expect(service).toBeTruthy();
  }));
});
