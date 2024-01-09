package diman.help;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import diman.help.exception.ColumnNotFoundException;
import diman.help.type.ChannelInfo;
import diman.help.type.Revenue;
import diman.help.type.RevenueContainer;
import diman.help.type.TaxesDetail;
import diman.help.type.Total;

public class Main {

	private static String ADS_VIDEO = "YouTube_Pladform_TV_M_20231001_video_summary_v1-1.csv";
	private static String SUBS_VIDEO = "YouTube_Pladform_TV_M_20231001_20231031_red_music_rawdata_video.csv";
	private static String SUBS_NON_MUSIC = "YouTube_Pladform_TV_M_20231001_20231031_red_rawdata_video_v1_1.csv";
	private static String ADS_SHORTS = "YouTube_Pladform_TV_M_20231101_20231130_monthly_shorts_non_music.csv";
	private static String SUBS_SHORTS = "YouTube_Pladform_TV_M_20231001_20231031_monthly_shorts_non_music.csv";
	private static String RIGHTS = "YouTube_Pladform_TV_M_20231001_ADJ_claim_raw_v1-1.csv";
	private static String TAXES_RETURN = "YouTube_Pladform_TV_M_202311_affiliate_tax_refund_summary_v1_0.csv";
	private static String TAXES_ALL = "YouTube_Pladform_TV_M_202311_affiliate_payment_summary_v1-0.csv";

	private static String TOTAL = "Тест-Tаблица 1.csv";

//	private static String DIR = "/home/anton/diman_files/";
	private static String DIR = "D:\\private\\diman-help\\diman-files\\";

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static List<ChannelInfo> channels = Lists.newArrayList();

	public static void main(String[] args) throws IOException, CsvException {
		LOGGER.debug("start");
		fillChannels();
		LOGGER.debug("channels was filled");

		process();

	}

	private static void process() throws IOException, CsvException {
		LOGGER.debug("start process");
		List<Total> totalList = Lists.newArrayList();

		ArrayDeque<Revenue> adsVideo = getRevenueList(new File(DIR + ADS_VIDEO).toPath(), 1);
		LOGGER.debug("adsVideo size = {}", adsVideo.size());
		ArrayDeque<Revenue> subsVideo = getRevenueList(new File(DIR + SUBS_VIDEO).toPath(), 2);
		LOGGER.debug("subsVideo size = {}", subsVideo.size());
		ArrayDeque<Revenue> subsNonMus = getRevenueList(new File(DIR + SUBS_NON_MUSIC).toPath(), 3);
		LOGGER.debug("subsNonMus size = {}", subsNonMus.size());
		ArrayDeque<Revenue> adsShorts = getRevenueList(new File(DIR + ADS_SHORTS).toPath(), 4);
		LOGGER.debug("adsShorts size = {}", adsShorts.size());
		ArrayDeque<Revenue> subsShorts = getRevenueList(new File(DIR + SUBS_SHORTS).toPath(), 5);
		LOGGER.debug("subsShorts size = {}", subsShorts.size());
		ArrayDeque<Revenue> rights = getRevenueList(new File(DIR + RIGHTS).toPath(), 6);
		LOGGER.debug("rights size = {}", rights.size());
		ArrayDeque<Revenue> taxesReturn = getRevenueList(new File(DIR + TAXES_RETURN).toPath(), 7);
		LOGGER.debug("taxesReturn size = {}", taxesReturn);
		ArrayDeque<Revenue> taxesAll = getRevenueList(new File(DIR + TAXES_ALL).toPath(), 8);
		LOGGER.debug("taxesAll size = {}", taxesAll.size());

		for (ChannelInfo channel : channels) {
			String id = channel.getId();

			BigDecimal videoAds = getRevSum(id, adsVideo);
			BigDecimal videoSubs = getRevSum(id, subsVideo);
			BigDecimal nonMusSubs = getRevSum(id, subsNonMus);
			BigDecimal shortsAds = getRevSum(id, adsShorts);
			BigDecimal shortsSubs = getRevSum(id, subsShorts);
			BigDecimal rightsTotal = getRevSum(id, rights);
			BigDecimal returnTaxes = getRevSum(id, taxesReturn);
			BigDecimal allTaxes = getRevSum(id, taxesAll);

			LOGGER.debug(
					"videoAds: {}, videoSubs: {}, nonMusSubs: {}, shortsAds: {}, "
							+ "shortsSubs: {}, rightsTotal: {}, returnTaxes: {}, allTaxes: {}",
					videoAds, videoSubs, nonMusSubs, shortsAds, shortsSubs, rightsTotal, returnTaxes, allTaxes);
		}
	}

//	private static TaxesDetail getTaxesDetail() {
//		
//	}

	private static BigDecimal getRevSum(String id, ArrayDeque<Revenue> revList) {
		BigDecimal total = new BigDecimal(0);

		for (Revenue rev : revList) {
			if (rev.getId().equals(id)) {
				total.add(rev.getRevenue());
			}
		}

		return total;
	}

	private static ArrayDeque<Revenue> getRevenueList(Path path, int type) throws IOException, CsvException {
		try {
			LOGGER.debug("start read csv: {}", path.getFileName());

			List<String[]> lines = readCsv(path);

			int headerInd = getHeaderInd(lines);
			LOGGER.debug("headerInd = {}", headerInd);

			int chanelIdColInd = getColInd(lines.get(headerInd), "Channel ID");
			LOGGER.debug("channdelIdColInd = {}", chanelIdColInd);

			int partnerRevColInd = getColInd(lines.get(headerInd), "Partner Revenue");
//			int taxWithheldColInd = getColInd(lines.get(headerInd), "Tax Withheld Amount");

//			ArrayDeque<Revenue> result = new ArrayDeque<>(lines.size());
			RevenueContainer cont = new RevenueContainer();
			int cnt = 0;
			int cntChunk = 0;
			lines.parallelStream().forEach(n -> cont.add(toRevenue(n, chanelIdColInd, partnerRevColInd)));
//			for (int i = headerInd; i < lines.size(); i++) {
//				String[] line = lines.get(i);
////				if (!isLineEmpty(line)) {
////					result.add(toRevenue(line, chanelIdColInd, 0));
////				cont.add(toRevenue(line, chanelIdColInd, 0));
////				}
//				if (++cnt == 60000) {
//					LOGGER.debug("line number = {}, cnt = {}, cntChunk = {}", i, cnt, ++cntChunk);
//					cnt = 0;
//				}
//			}

			return cont.getList();
		} catch (ColumnNotFoundException e) {
			LOGGER.error("column not found, file = {}", path.getFileName(), e);
			return new ArrayDeque<>();
		}

	}

	private static int getHeaderInd(List<String[]> lines) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).length > 1) {
				return i;
			}
		}

		LOGGER.warn("header not found");
		return 0;
	}

	private static int getColInd(String[] line, String colName) throws ColumnNotFoundException {
		for (int i = 0; i < line.length; i++) {
			if (line[i].trim().equals(colName)) {
				return i;
			}
		}
		throw new ColumnNotFoundException(colName);
	}

	private static Revenue toRevenue(String[] line, int chanelIdInd, int partnerColInd) {

		try {
			String id;
			BigDecimal revenue = null;
//			Double revenue = null;
//		BigDecimal taxWithheldAmount = null;

			int revenueInd = line.length - 1;

			String idStr = line[chanelIdInd];
			String revStr = line[partnerColInd];
			LOGGER.debug("idStr = {}, revStr = {}", idStr, revStr);
//		String taxWithheldStr = line[taxWithheldInd];

			try {
//				id = line[chanelIdInd];
				revenue = new BigDecimal(revStr);
//				revenue = Double.parseDouble(revStr);
//			taxWithheldAmount = new BigDecimal(taxWithheldStr);
			} catch (Exception e) {
				LOGGER.warn("exc = {}", e.getMessage());
//			LOGGER.warn("id = {}, revenue = {}, taxWithheldAmount = {}", idStr, revStr, taxWithheldStr);
				LOGGER.warn("id = {}, revenue = {}", idStr, revStr);
			}

//		return new Revenue(idStr, revenue, taxWithheldAmount);
			return new Revenue(idStr, revenue);

		} catch (Exception e) {
			LOGGER.error("exception in toRevenue", e);
			return new Revenue("", new BigDecimal(0));
		}
	}

	private static int getIdIndex(int type) {
		switch (type) {
		case 1:
			return 5;
		case 2:
			return 11;
		case 3:
			return 11;
		case 4:
			return 5;
		case 5:
			return 10;
		case 6:
			return 4;
		case 7:
			return 1;
		case 8:
			return 1;
		default:
			return 0;
		}
	}

	private static boolean isHeader(String[] line) {

		String lastColumn = line[line.length - 1];
		if (lastColumn.equals("Partner Revenue")) {
			LOGGER.warn("line is header, last column = {}");
			return true;
		}
		return false;
	}

	private static void fillChannels() throws IOException, CsvException {
		Path path = new File(DIR + TOTAL).toPath();
		List<String[]> lines = readCsv(path);
		LOGGER.debug("lines size = {}", lines.size());

		for (int i = 5; i < lines.size(); i++) {
			String[] line = lines.get(i);
			if (isLineEmpty(line)) {
				LOGGER.warn("line is empty");
			} else {
				channels.add(toChannel(lines.get(i)));
			}
		}

		LOGGER.debug("channels size = {}", channels.size());
	}

	private static List<String[]> readCsv(Path path) throws IOException, CsvException {
		try (Reader reader = Files.newBufferedReader(path)) {
			try (CSVReader csvReader = new CSVReader(reader)) {
				List<String[]> lines = csvReader.readAll();
				LOGGER.debug("lines size = {}", lines.size());
				return lines;
			}
		}
	}

	private static String[] getHeader(Path path) throws IOException, CsvValidationException {
		try (Reader reader = Files.newBufferedReader(path)) {
			try (CSVReader csvReader = new CSVReader(reader)) {
				String[] line;
				while ((line = csvReader.readNext()) != null) {
					if (line.length > 2) {
						return line;
					}
				}
			}
		}
		return null;
	}

	private static ChannelInfo toChannel(String[] line) {
		String id = line[0];
		String name = line[1];
		String contrAgent = line[2];
		String rateStr = line[3];
		double rate = 0;
		try {
			rate = Double.valueOf(rateStr);

		} catch (NumberFormatException e) {
//			LOGGER.warn("id size = {}", id.length());
			LOGGER.warn("wrong rate format, id = {}, name = {}, contrAgent = {}, rate = {}", id, name, contrAgent,
					rateStr);
		}

		return new ChannelInfo(id, name, contrAgent, rate);
	}

	private static boolean isLineEmpty(String[] line) {
		return line[0].isEmpty();
	}

}
