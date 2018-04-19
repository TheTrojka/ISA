import { TestBed, inject } from '@angular/core/testing';

import { HappeningService } from './happening.service';

describe('HappeningService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HappeningService]
    });
  });

  it('should be created', inject([HappeningService], (service: HappeningService) => {
    expect(service).toBeTruthy();
  }));
});
